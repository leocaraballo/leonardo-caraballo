package com.leo.bootcampglobant.services;

import static org.junit.Assert.assertEquals;

import com.leo.bootcampglobant.models.Order;
import com.leo.bootcampglobant.models.OrderLine;
import com.leo.bootcampglobant.models.Product;
import com.leo.bootcampglobant.repositories.OrderLineRepository;
import com.leo.bootcampglobant.repositories.OrderRepository;
import com.leo.bootcampglobant.repositories.ProductRepository;
import com.leo.bootcampglobant.repositories.RepositoryInMemory;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;

public class ShoppingCartServiceImplTest {

  private class FakeOrderLineRepository
      extends RepositoryInMemory<OrderLine>
      implements OrderLineRepository {

    @Override
    public void clear() {
      this.entities = new ArrayList<>();
    }

  }

  private class FakeOrderRepository
      extends  RepositoryInMemory<Order>
      implements OrderRepository {

  }

  private class FakeProductRepository
      extends RepositoryInMemory<Product>
      implements ProductRepository {

  }

  private ShoppingCartServiceImpl shoppingCartService;

  private static final Product[] testProducts = {
      new Product( "Smart TV 42'", "Electronics", new BigDecimal("149.99")),
      new Product("Soda Cola 3L", "Grocery", new BigDecimal("14.49")),
      new Product("New Phone X", "Electronics", new BigDecimal("459.99")),
      new Product("Bread 1Kg", "Grocery", new BigDecimal("1.99")),
      new Product("Generic Rap Songs", "Music", new BigDecimal("11.49"))
  };

  @Before
  public void initialize() {
    FakeProductRepository productRepository = new FakeProductRepository();
    ProductService productService = new ProductServiceImpl(productRepository);

    for (int i = 0; i < testProducts.length; ++i) {
      testProducts[i] = productRepository.create(testProducts[i]);
    }

    FakeOrderLineRepository orderLineRepository = new FakeOrderLineRepository();
    FakeOrderRepository orderRepository = new FakeOrderRepository();

    shoppingCartService = new ShoppingCartServiceImpl(orderLineRepository,
                                                      orderRepository,
                                                      productService);
  }

  @Test
  public void getCurrentCart_correctOrderContent() {
    shoppingCartService.addToCart(testProducts[0], 1);
    shoppingCartService.addToCart(testProducts[1], 6);
    shoppingCartService.addToCart(testProducts[2], 2);

    List<OrderLine> expectedOrderLines = Arrays.asList(
        new OrderLine(testProducts[0], 1),
        new OrderLine(testProducts[1], 6),
        new OrderLine(testProducts[2], 2)
    );

    assertEquals(expectedOrderLines, shoppingCartService.getCurrentCart().getOrderList());
  }

  @Test
  public void getOrders_emptyAtFirst() {
    assertEquals(Collections.emptyList(), shoppingCartService.getOrders());
  }

  @Test
  public void getOrders_afterCheckOut() {
    shoppingCartService.addToCart(testProducts[0], 1);
    shoppingCartService.addToCart(testProducts[1], 6);
    shoppingCartService.addToCart(testProducts[2], 2);

    List<OrderLine> expectedOrderLines = Arrays.asList(
        new OrderLine(testProducts[0], 1),
        new OrderLine(testProducts[1], 6),
        new OrderLine(testProducts[2], 2)
    );

    shoppingCartService.checkOut();

    assertEquals(1, shoppingCartService.getOrders().size());
    assertEquals(expectedOrderLines, shoppingCartService.getOrders().get(0).getOrderList());
  }

  @Test
  public void getOrderLine_existing() {
    long insertedId = shoppingCartService.addToCart(testProducts[0], 5).getId();
    assertEquals(new OrderLine(testProducts[0], 5),
        shoppingCartService.getOrderLine(insertedId));
  }

  @Test
  public void getOrderLine_correctCost() {
    long insertedId = shoppingCartService.addToCart(testProducts[0], 5).getId();

    assertEquals(testProducts[0].getPrice().multiply(new BigDecimal("5")),
                 shoppingCartService.getOrderLine(insertedId).getCost());
  }

  @Test(expected = NoSuchElementException.class)
  public void removeFromCart_existing() {
    long idToRemove = shoppingCartService.addToCart(testProducts[0], 10).getId();

    shoppingCartService.removeFromCart(idToRemove);
    shoppingCartService.getOrderLine(idToRemove);
  }

  @Test(expected = NoSuchElementException.class)
  public void removeFromCartByProductId_productId() {
    long removedId = shoppingCartService.addToCart(testProducts[0], 5).getId();
    shoppingCartService.removeFromCartByProductId(testProducts[0].getId());
    shoppingCartService.getOrderLine(removedId);
  }

  @Test
  public void clear_emptyAfterCall() {
    shoppingCartService.addToCart(testProducts[0], 1);
    shoppingCartService.addToCart(testProducts[1], 6);
    shoppingCartService.addToCart(testProducts[2], 2);

    shoppingCartService.clear();
    assertEquals(Collections.emptyList(), shoppingCartService.getCurrentCart().getOrderList());
  }

  @Test
  public void checkOut_emptyAfterCall() {
    shoppingCartService.addToCart(testProducts[0], 1);
    shoppingCartService.addToCart(testProducts[1], 6);
    shoppingCartService.addToCart(testProducts[2], 2);

    shoppingCartService.checkOut();
    assertEquals(Collections.emptyList(), shoppingCartService.getCurrentCart().getOrderList());
  }

  @Test
  public void checkOut_correctReturn() {
    shoppingCartService.addToCart(testProducts[0], 1);
    shoppingCartService.addToCart(testProducts[1], 6);
    shoppingCartService.addToCart(testProducts[2], 2);

    List<OrderLine> expectedOrderLines = Arrays.asList(
        new OrderLine(testProducts[0], 1),
        new OrderLine(testProducts[1], 6),
        new OrderLine(testProducts[2], 2)
    );
    assertEquals(expectedOrderLines, shoppingCartService.checkOut().getOrderList());
  }

  @Test
  public void checkOut_correctTotalCost() {
    shoppingCartService.addToCart(testProducts[0], 1);
    shoppingCartService.addToCart(testProducts[1], 6);
    shoppingCartService.addToCart(testProducts[2], 2);

    assertEquals(new BigDecimal("1156.91"), shoppingCartService.checkOut().getTotalCost());
  }

  @Test
  public void checkOut_correctTotalCostAfterRemoval() {
    shoppingCartService.addToCart(testProducts[0], 1);
    shoppingCartService.addToCart(testProducts[1], 6);
    shoppingCartService.addToCart(testProducts[2], 2);

    shoppingCartService.removeFromCartByProductId(testProducts[1].getId());

    assertEquals(new BigDecimal("1069.97"), shoppingCartService.checkOut().getTotalCost());
  }

}