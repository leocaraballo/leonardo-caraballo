package com.leo.bootcampglobant.services;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.leo.bootcampglobant.exceptions.EmptyCartException;
import com.leo.bootcampglobant.exceptions.OrderLineNotFoundException;
import com.leo.bootcampglobant.exceptions.ProductAlreadyInCartException;
import com.leo.bootcampglobant.exceptions.ProductNotFoundException;
import com.leo.bootcampglobant.exceptions.UserNotFoundException;
import com.leo.bootcampglobant.models.Order;
import com.leo.bootcampglobant.models.OrderLine;
import com.leo.bootcampglobant.models.Product;
import com.leo.bootcampglobant.models.ShoppingCart;
import com.leo.bootcampglobant.models.User;
import com.leo.bootcampglobant.repositories.OrderLineRepository;
import com.leo.bootcampglobant.repositories.ShoppingCartRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ShoppingCartServiceImplTest {

  @InjectMocks
  ShoppingCartServiceImpl shoppingCartService;
  @Mock
  ShoppingCartRepository mockedShoppingCartRepository;
  @Mock
  OrderLineRepository mockedOrderLineRepository;
  @Mock
  ProductService mockedProductService;
  @Mock
  UserService mockedUserService;

  private ShoppingCart testShoppingCart = new ShoppingCart(
      // need to instantiate an ArrayList because Arrays.asList() returns a immutable list.
      new ArrayList<>(Arrays.asList(
          new OrderLine(1L, new Product(1L, "Test product 1", new BigDecimal("399.99")), 5),
          new OrderLine(2L, new Product(2L, "Test product 2", new BigDecimal("249.99")), 2),
          new OrderLine(3L, new Product(3L, "Test product 3", new BigDecimal("175.00")), 1)
      )),
      new User(1L, Collections.emptyList(), "Eamon", "Andrew", "eamondrew", "password")
  );

  @Before
  public void initialize() {
    MockitoAnnotations.initMocks(this);

    when(mockedShoppingCartRepository.findById(anyLong()))
        .thenReturn(Optional.of(testShoppingCart));
  }

  @Test
  public void getShopingCartByUserId_correctRetrieve() {

    assertEquals(testShoppingCart, shoppingCartService.getShopingCartByUserId(1L));
    verify(mockedShoppingCartRepository).findById(1L);
  }

  @Test(expected = UserNotFoundException.class)
  public void getShopingCartByUserId_userNotFound() {
    when(mockedShoppingCartRepository.findById(anyLong())).thenReturn(Optional.empty());
    try {
      shoppingCartService.getShopingCartByUserId(1L);
    } finally {
      verify(mockedShoppingCartRepository).findById(1L);
    }
  }

  @Test
  public void getOrderLineById_correctRetrieve() {
    assertEquals(testShoppingCart.getCurrentItems().get(0),
        shoppingCartService.getOrderLineById(1L, 1L));
  }

  @Test(expected = OrderLineNotFoundException.class)
  public void getOrderLineById_orderLineNotFound() {
    shoppingCartService.getOrderLineById(1L, 8L);
  }

  @Test
  public void getOrderLineByProductId_correctRetrieve() {
    assertEquals(testShoppingCart.getCurrentItems().get(0),
        shoppingCartService.getOrderLineByProductId(1L, 1L));
  }

  @Test(expected = OrderLineNotFoundException.class)
  public void getOrderLineByProductId_orderLineNotFound() {
    shoppingCartService.getOrderLineByProductId(1L, 8L);
  }

  @Test
  public void addToCart_correctInsertion() {
    Product insertedProduct = new Product(4L, "Test product 4", new BigDecimal("19.99"));
    OrderLine insertedOrderLine = new OrderLine(4L, insertedProduct, 10);

    when(mockedProductService.getProductById(anyLong())).thenReturn(insertedProduct);
    when(mockedOrderLineRepository.save(any())).thenReturn(insertedOrderLine);

    assertEquals(insertedOrderLine, shoppingCartService.addToCart(1L, 4L, 10));
    List<OrderLine> items = shoppingCartService.getShopingCartByUserId(1L).getCurrentItems();
    assertEquals(insertedOrderLine, items.get(items.size() - 1));
    verify(mockedShoppingCartRepository).save(any());
  }

  @Test(expected = ProductNotFoundException.class)
  public void addToCart_productNotFound() {
    when(mockedProductService.getProductById(anyLong())).thenThrow(ProductNotFoundException.class);

    shoppingCartService.addToCart(1L, 4L, 10);
  }

  @Test(expected = ProductAlreadyInCartException.class)
  public void addToCart_productAlreadyInCart() {
    Product insertedProduct = testShoppingCart.getCurrentItems().get(0).getProduct();
    when(mockedProductService.getProductById(anyLong()))
        .thenReturn(insertedProduct);

    shoppingCartService.addToCart(1L, 1L, 99);
  }

  @Test
  public void deleteFromCartByOrderLineId_correctDeletion() {
    OrderLine deletedOrderLine = testShoppingCart.getCurrentItems().get(0);
    assertTrue(shoppingCartService.deleteFromCartByOrderLineId(1L, 1L));
    assertFalse(shoppingCartService.getShopingCartByUserId(1L).getCurrentItems()
        .contains(deletedOrderLine));
    verify(mockedShoppingCartRepository).save(any());
    verify(mockedOrderLineRepository).deleteById(1L);
  }

  @Test
  public void deleteFromCartByOrderLineId_noOrderLineFound() {
    List<OrderLine> originalItems = List.copyOf(testShoppingCart.getCurrentItems());
    assertFalse(shoppingCartService.deleteFromCartByOrderLineId(1L, 500L));
    assertEquals(originalItems, shoppingCartService.getShopingCartByUserId(1L).getCurrentItems());
    verify(mockedShoppingCartRepository, never()).save(any());
    verify(mockedOrderLineRepository, never()).deleteById(any());
  }

  @Test
  public void deleteFromCartByProductId_correctDeletion() {
    OrderLine deletedOrderLine = testShoppingCart.getCurrentItems().get(0);
    assertTrue(shoppingCartService.deleteFromCartByProductId(1L, 1L));
    assertFalse(shoppingCartService.getShopingCartByUserId(1L).getCurrentItems()
        .contains(deletedOrderLine));
    verify(mockedShoppingCartRepository).save(any());
    verify(mockedOrderLineRepository).delete(deletedOrderLine);
  }

  @Test
  public void deleteFromCartByProductId_noOrderLineProductFound() {
    List<OrderLine> originalItems = List.copyOf(testShoppingCart.getCurrentItems());
    assertFalse(shoppingCartService.deleteFromCartByProductId(1L, 500L));
    assertEquals(originalItems, shoppingCartService.getShopingCartByUserId(1L).getCurrentItems());
    verify(mockedShoppingCartRepository, never()).save(any());
    verify(mockedOrderLineRepository, never()).delete(any());
  }

  @Test
  public void clear_emptyList() {
    shoppingCartService.clear(1L);
    assertEquals(Collections.emptyList(),
        shoppingCartService.getShopingCartByUserId(1L).getCurrentItems());
    verify(mockedShoppingCartRepository).save(any());
    verify(mockedOrderLineRepository).deleteAll(any());
  }

  @Test
  public void checkOut_emptyListAfter() {
    shoppingCartService.checkOut(1L);
    assertEquals(Collections.emptyList(),
        shoppingCartService.getShopingCartByUserId(1L).getCurrentItems());
    verify(mockedShoppingCartRepository).save(any());
  }

  @Test
  public void checkOut_correctProducedOrder() {
    Order order = testShoppingCart.produceOrder();
    when(mockedUserService.createOrder(anyLong(), any())).then(AdditionalAnswers.returnsLastArg());


    Order checkOutOrder = shoppingCartService.checkOut(1L);
    assertEquals(order.getTotalCost(), checkOutOrder.getTotalCost());
    assertEquals(new BigDecimal("2674.93"), checkOutOrder.getTotalCost());
    verify(mockedUserService).createOrder(eq(1L), any());
  }

  @Test(expected = EmptyCartException.class)
  public void checkOut_emptyCart() {
    shoppingCartService.clear(1L);
    shoppingCartService.checkOut(1L);
  }

}