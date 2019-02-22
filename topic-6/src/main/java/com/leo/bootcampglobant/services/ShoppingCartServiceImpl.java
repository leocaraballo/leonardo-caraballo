package com.leo.bootcampglobant.services;

import com.leo.bootcampglobant.exceptions.EmptyCartException;
import com.leo.bootcampglobant.exceptions.OrderLineNotFoundException;
import com.leo.bootcampglobant.exceptions.ProductAlreadyInCartException;
import com.leo.bootcampglobant.exceptions.UserNotFoundException;
import com.leo.bootcampglobant.models.Order;
import com.leo.bootcampglobant.models.OrderLine;
import com.leo.bootcampglobant.models.Product;
import com.leo.bootcampglobant.models.ShoppingCart;
import com.leo.bootcampglobant.repositories.OrderLineRepository;
import com.leo.bootcampglobant.repositories.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

  private final ShoppingCartRepository shoppingCartRepository;
  private final OrderLineRepository orderLineRepository;
  private final ProductService productService;
  private final UserService userService;

  @Autowired
  public ShoppingCartServiceImpl(
      ShoppingCartRepository shoppingCartRepository,
      OrderLineRepository orderLineRepository,
      ProductService productService, UserService userService) {

    this.shoppingCartRepository = shoppingCartRepository;
    this.orderLineRepository = orderLineRepository;
    this.productService = productService;
    this.userService = userService;
  }

  @Override
  public ShoppingCart getShopingCartByUserId(Long userId) {
    return shoppingCartRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("id of " + userId));
  }

  @Override
  public OrderLine getOrderLineById(Long userId, Long orderLineId) {
    return getShopingCartByUserId(userId).getCurrentItems().stream()
        .filter(e -> e.getId().equals(orderLineId)).findFirst()
        .orElseThrow(() -> new OrderLineNotFoundException("id of " + orderLineId));
  }

  @Override
  public OrderLine getOrderLineByProductId(Long userId, Long productId) {
    return getShopingCartByUserId(userId).getCurrentItems().stream()
        .filter(e -> e.getProduct().getId().equals(productId)).findFirst()
        .orElseThrow(() -> new OrderLineNotFoundException("product with id of " + productId));
  }

  @Override
  public OrderLine addToCart(Long userId, Long productId, int quantity) {
    Product product = productService.getProductById(productId);
    ShoppingCart shoppingCart = getShopingCartByUserId(userId);

    try {
      this.getOrderLineByProductId(userId, productId);
    } catch (OrderLineNotFoundException e) {
      OrderLine orderLine = orderLineRepository
          .save(new OrderLine(product, quantity));
      shoppingCart.getCurrentItems().add(orderLine);
      shoppingCartRepository.save(shoppingCart);
      return orderLine;
    }
    // The product is already in the cart
    throw new ProductAlreadyInCartException(productId);
  }

  @Override
  public boolean deleteFromCartByOrderLineId(Long userId, Long orderLineId) {
    ShoppingCart shoppingCart = getShopingCartByUserId(userId);
    boolean removed = shoppingCart.getCurrentItems().
        removeIf(e -> e.getId().equals(orderLineId));
    if (removed) {
      shoppingCartRepository.save(shoppingCart);
      orderLineRepository.deleteById(orderLineId);
    }
    return removed;
  }

  @Override
  public boolean deleteFromCartByProductId(Long userId, Long productId) {
    ShoppingCart shoppingCart = getShopingCartByUserId(userId);
    OrderLine removed = shoppingCart.getCurrentItems().stream()
        .filter(e -> e.getProduct().getId().equals(productId)).findFirst().orElse(null);
    if (removed == null) {
      return false;
    }
    shoppingCart.getCurrentItems().remove(removed);
    shoppingCartRepository.save(shoppingCart);
    orderLineRepository.delete(removed);
    return true;
  }

  @Override
  public void clear(Long userId) {
    clear(userId, true);
  }

  private void clear(Long userId, boolean removeChild) {
    clear(getShopingCartByUserId(userId), removeChild);
  }

  private void clear(ShoppingCart shoppingCart, boolean removeChild) {
    if (removeChild) {
      orderLineRepository.deleteAll(shoppingCart.getCurrentItems());
    }
    shoppingCart.getCurrentItems().clear();
    shoppingCartRepository.save(shoppingCart);
  }

  @Override
  public Order checkOut(Long userId) {
    ShoppingCart shoppingCart = getShopingCartByUserId(userId);

    if (shoppingCart.getCurrentItems().isEmpty()) {
      throw new EmptyCartException();
    }
    Order order = userService.createOrder(userId, shoppingCart.produceOrder());
    clear(shoppingCart, false);
    return order;
  }
}
