package com.leo.bootcampglobant.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.leo.bootcampglobant.exceptions.OrderNotFoundException;
import com.leo.bootcampglobant.models.Order;
import com.leo.bootcampglobant.models.OrderLine;
import com.leo.bootcampglobant.models.Product;
import com.leo.bootcampglobant.models.User;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class OrderServiceImplTest {

  @InjectMocks
  private OrderServiceImpl orderService;
  @Mock
  private UserService mockedUserService;

  private static final User testUser = new User(1L, Arrays.asList(
      new Order(1L, Arrays.asList(
          new OrderLine(1L, new Product(1L, "Test Product 1", new BigDecimal("199.99")), 5),
          new OrderLine(2L, new Product(2L, "Test Product 2", new BigDecimal("499.99")), 2),
          new OrderLine(3L, new Product(3L, "Test Product 3", new BigDecimal("50.00")), 7)
      ), LocalDateTime.now()),
      new Order(2L, Arrays.asList(
          new OrderLine(4L, new Product(1L, "Test Product 1", new BigDecimal("199.99")), 3),
          new OrderLine(5L, new Product(4L, "Test Product 4", new BigDecimal("2.99")), 15)
          ), LocalDateTime.now())),
      "Test First Name", "Test Last Name", "testusername", "testpassword"
  );

  public static final BigDecimal[] correctCosts = {new BigDecimal("2349.93"), new BigDecimal("644.82")};

  @Before
  public void initialize() {
    MockitoAnnotations.initMocks(this);
    when(mockedUserService.getUserById(anyLong())).thenReturn(testUser);
  }

  @Test
  public void getOrderById_correctRetrieve() {
    assertEquals(testUser.getOrders().get(0), orderService.getOrderById(1L, 1L));
  }

  @Test(expected = OrderNotFoundException.class)
  public void getOrderById_orderNotFound() {
    assertEquals(testUser.getOrders().get(0), orderService.getOrderById(1L, 500L));
  }

  @Test
  public void getUserOrders_correctRetrieve() {
    assertEquals(testUser.getOrders(), orderService.getUserOrders(1L));
  }

  @Test
  public void getUsersOrders_correctCost() {
    List<Order> actualOrders = orderService.getUserOrders(1L);

    for (int i = 0; i < actualOrders.size(); ++i) {
      BigDecimal cost = actualOrders.get(i).getTotalCost();
      assertEquals(testUser.getOrders().get(i).getTotalCost(), cost);
      assertEquals(correctCosts[i], cost);
    }
  }
}