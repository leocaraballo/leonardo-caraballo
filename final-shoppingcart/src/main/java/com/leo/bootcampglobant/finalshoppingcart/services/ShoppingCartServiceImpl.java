package com.leo.bootcampglobant.finalshoppingcart.services;

import com.leo.bootcampglobant.finalshoppingcart.exceptions.EmptyCartException;
import com.leo.bootcampglobant.finalshoppingcart.exceptions.OrderLineNotFoundException;
import com.leo.bootcampglobant.finalshoppingcart.exceptions.ProductAlreadyInCartException;
import com.leo.bootcampglobant.finalshoppingcart.exceptions.PropertyValidationException;
import com.leo.bootcampglobant.finalshoppingcart.exceptions.UserNotFoundException;
import com.leo.bootcampglobant.finalshoppingcart.models.CartItem;
import com.leo.bootcampglobant.finalshoppingcart.models.Order;
import com.leo.bootcampglobant.finalshoppingcart.models.Product;
import com.leo.bootcampglobant.finalshoppingcart.models.ShoppingCart;
import com.leo.bootcampglobant.finalshoppingcart.repositories.CartItemRepository;
import com.leo.bootcampglobant.finalshoppingcart.repositories.ShoppingCartRepository;
import java.util.ArrayList;
import javax.validation.ConstraintViolationException;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

  private final ShoppingCartRepository shoppingCartRepository;
  private final CartItemRepository cartItemRepository;
  private final ProductService productService;
  private final UserService userService;

  public ShoppingCartServiceImpl(
      ShoppingCartRepository shoppingCartRepository,
      CartItemRepository cartItemRepository,
      ProductService productService, UserService userService) {

    this.shoppingCartRepository = shoppingCartRepository;
    this.cartItemRepository = cartItemRepository;
    this.productService = productService;
    this.userService = userService;
  }

  @Override
  public ShoppingCart getShopingCartByUserId(Long userId) {
    return shoppingCartRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("id of " + userId));
  }

  @Override
  public CartItem getCartItemById(Long userId, Long orderLineId) {
    return getShopingCartByUserId(userId).getCurrentItems().stream()
        .filter(e -> e.getId().equals(orderLineId)).findFirst()
        .orElseThrow(() -> new OrderLineNotFoundException("id of " + orderLineId));
  }

  private static CartItem getCartItemByProductIdFromCart(ShoppingCart shoppingCart,
      Long productId) {

    return shoppingCart.getCurrentItems().stream()
        .filter(e -> e.getProduct().getId().equals(productId)).findFirst()
        .orElseThrow(() -> new OrderLineNotFoundException("product with id of " + productId));
  }

  @Override
  public CartItem getCartItemByProductId(Long userId, Long productId) {
    return getCartItemByProductIdFromCart(getShopingCartByUserId(userId), productId);
  }


  @Override
  public CartItem addToCart(Long userId, Long productId, int quantity) {
    Product product = productService.getProductById(productId);
    ShoppingCart shoppingCart = getShopingCartByUserId(userId);

    try {
      getCartItemByProductIdFromCart(shoppingCart, productId);
    } catch (OrderLineNotFoundException e) {
      CartItem cartItem;
      try {
        cartItem = cartItemRepository.save(new CartItem(product, quantity));
      } catch (ConstraintViolationException cve) {
        var validationInfo = new ArrayList<>(cve.getConstraintViolations()).get(0);
        String field = validationInfo.getPropertyPath().toString();
        String message = validationInfo.getMessage();
        throw new PropertyValidationException(field, message);
      }
      shoppingCart.getCurrentItems().add(cartItem);
      shoppingCartRepository.save(shoppingCart);
      return cartItem;
    }
    // The product is already in the cart
    throw new ProductAlreadyInCartException(productId);
  }

  @Override
  public boolean deleteFromCartByCartItemId(Long userId, Long orderLineId) {
    ShoppingCart shoppingCart = getShopingCartByUserId(userId);
    boolean removed = shoppingCart.getCurrentItems().
        removeIf(e -> e.getId().equals(orderLineId));
    if (removed) {
      cartItemRepository.deleteById(orderLineId);
      shoppingCartRepository.save(shoppingCart);
    }
    return removed;
  }

  @Override
  public boolean deleteFromCartByProductId(Long userId, Long productId) {
    ShoppingCart shoppingCart = getShopingCartByUserId(userId);
    CartItem removed = shoppingCart.getCurrentItems().stream()
        .filter(e -> e.getProduct().getId().equals(productId)).findFirst().orElse(null);
    if (removed == null) {
      return false;
    }
    shoppingCart.getCurrentItems().remove(removed);
    cartItemRepository.delete(removed);
    shoppingCartRepository.save(shoppingCart);
    return true;
  }

  private void clear(ShoppingCart shoppingCart) {
    cartItemRepository.deleteAll(shoppingCart.getCurrentItems());
    shoppingCart.getCurrentItems().clear();
    shoppingCartRepository.save(shoppingCart);
  }

  @Override
  public void clear(Long userId) {
    clear(this.getShopingCartByUserId(userId));
  }

  @Override
  public Order checkOut(Long userId) {
    ShoppingCart shoppingCart = getShopingCartByUserId(userId);

    if (shoppingCart.getCurrentItems().isEmpty()) {
      throw new EmptyCartException();
    }
    Order order = userService.createOrder(userId, shoppingCart.produceOrder());
    clear(shoppingCart);
    return order;
  }
}
