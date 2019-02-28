package com.leo.bootcampglobant.finalshoppingcart.controllers;

import com.leo.bootcampglobant.finalshoppingcart.models.CartItem;
import com.leo.bootcampglobant.finalshoppingcart.models.Order;
import com.leo.bootcampglobant.finalshoppingcart.models.ShoppingCart;
import com.leo.bootcampglobant.finalshoppingcart.services.OrderService;
import com.leo.bootcampglobant.finalshoppingcart.services.ShoppingCartService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ShoppingCartController.BASE_URL)
public class ShoppingCartController {

  public static final String BASE_URL = "/api/shoppingcart/{userId}";

  private final ShoppingCartService shoppingCartService;
  private final OrderService orderService;

  public ShoppingCartController(ShoppingCartService shoppingCartService,
      OrderService orderService) {

    this.shoppingCartService = shoppingCartService;
    this.orderService = orderService;
  }

  @GetMapping
  public ShoppingCart getShoppingCartByUserId(@PathVariable Long userId) {
    return shoppingCartService.getShopingCartByUserId(userId);
  }

  @GetMapping(params = "cartItemId")
  public CartItem getCartItemById(@PathVariable Long userId,
      @RequestParam(value = "cartItemId") Long cartItemId) {

    return shoppingCartService.getCartItemById(userId, cartItemId);
  }

  @GetMapping(params = "productId")
  public CartItem getCartItemByProductId(@PathVariable Long userId,
      @RequestParam(value = "productId") Long productId) {

    return shoppingCartService.getCartItemByProductId(userId, productId);
  }

  @PostMapping(params = {"productId", "quantity"})
  public CartItem addToCart(@PathVariable Long userId,
      @RequestParam(value = "productId") Long productId,
      @RequestParam(value = "quantity") int quantity) {

    return shoppingCartService.addToCart(userId, productId, quantity);
  }

  @DeleteMapping(params = "cartItemId")
  public boolean deleteFromCartByCartItemId(@PathVariable Long userId,
      @RequestParam(value = "cartItemId") Long cartItemId) {

    return shoppingCartService.deleteFromCartByCartItemId(userId, cartItemId);
  }

  @DeleteMapping(params = "productId")
  public boolean deleteFromCartByProductId(@PathVariable Long userId,
      @RequestParam(value = "productId") Long productId) {

    return shoppingCartService.deleteFromCartByProductId(userId, productId);
  }

  @DeleteMapping("/clear")
  public void clear(@PathVariable Long userId) {
    shoppingCartService.clear(userId);
  }

  @PostMapping("/checkout")
  public Order checkOut(@PathVariable Long userId) {
    return shoppingCartService.checkOut(userId);
  }

  @GetMapping("/orders")
  public List<Order> getUserOrders(@PathVariable Long userId) {
    return orderService.getUserOrders(userId);
  }

  @GetMapping(value = "/orders", params = "orderId")
  public Order getOrderById(@PathVariable Long userId,
      @RequestParam(value = "orderId") Long orderId) {

    return orderService.getOrderById(userId, orderId);
  }

}
