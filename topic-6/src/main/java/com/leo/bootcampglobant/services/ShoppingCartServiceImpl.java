package com.leo.bootcampglobant.services;

import com.leo.bootcampglobant.models.Order;
import com.leo.bootcampglobant.models.OrderLine;
import com.leo.bootcampglobant.models.Product;
import com.leo.bootcampglobant.repositories.OrderLineRepository;
import com.leo.bootcampglobant.repositories.OrderLineRepositoryInMemory;
import com.leo.bootcampglobant.repositories.OrderRepository;
import com.leo.bootcampglobant.repositories.OrderRepositoryInMemory;
import com.leo.bootcampglobant.repositories.ProductRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

  private OrderLineRepository orderLineRepository;
  private OrderRepository orderRepository;
  private ProductService productRepository;

  public ShoppingCartServiceImpl(OrderLineRepository orderLineRepository,
      OrderRepository orderRepository,
      ProductService productRepository) {

    this.orderLineRepository = orderLineRepository;
    this.orderRepository = orderRepository;
    this.productRepository = productRepository;
  }

  public ShoppingCartServiceImpl(ProductService productRepository) {
    this(new OrderLineRepositoryInMemory(),
         new OrderRepositoryInMemory(),
         productRepository);
  }

  public ShoppingCartServiceImpl() {
    this(new ProductServiceImpl());
  }

  @Override
  public Order getCurrentCart() {
    return new Order(orderLineRepository.readAll(), LocalDateTime.now());
  }

  @Override
  public List<Order> getOrders() {
    return orderRepository.readAll();
  }

  @Override
  public OrderLine getOrderLine(long id) {
    return orderLineRepository.readByKey(id).get();
  }

  @Override
  public OrderLine addToCart(Product product, int quantity) {
    if (productRepository.getProduct(product.getId()) == null) {
        throw new RuntimeException("The product " + product + " doesn't exist.");
    }

    OrderLine orderLine = new OrderLine(product, quantity);
    int index = orderLineRepository.readAll().indexOf(product);

    if (index < 0) {
      // doesnt exist
      orderLine = orderLineRepository.create(orderLine);
    } else {
      orderLine = orderLineRepository.readAll().get(index);
      orderLine = new OrderLine(orderLine.getId(), product,
          orderLine.getQuantity() + quantity);
      orderLineRepository.update(orderLine);
    }
    return orderLine;
  }

  @Override
  public void removeFromCart(long id) {
    orderLineRepository.deleteByKey(id);
  }

  @Override
  public void removeFromCartByProductId(long id) {
    Optional<OrderLine> result = orderLineRepository.readAll().stream().
        filter(e -> e.getProduct().getId() == id).findFirst();

    result.ifPresent(orderLine -> orderLineRepository.delete(orderLine));
  }

  @Override
  public void clear() {
    orderLineRepository.clear();
  }

  @Override
  public Order checkOut() {
    Order newOrder = new Order(orderLineRepository.readAll(), LocalDateTime.now());
    orderRepository.create(newOrder);
    clear();
    return newOrder;
  }
}
