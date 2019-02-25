package com.leo.bootcampglobant.controllers;

import com.leo.bootcampglobant.models.Order;
import com.leo.bootcampglobant.models.OrderLine;
import com.leo.bootcampglobant.models.ShoppingCart;
import com.leo.bootcampglobant.services.OrderService;
import com.leo.bootcampglobant.services.ShoppingCartService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

  @GetMapping(params = "orderLineId")
  public OrderLine getOrderLineById(@PathVariable Long userId,
      @RequestParam(value = "orderLineId") Long orderLineId) {

    return shoppingCartService.getOrderLineById(userId, orderLineId);
  }

  @GetMapping(params = "productId")
  public OrderLine getOrderLineByProductId(@PathVariable Long userId,
      @RequestParam(value = "productId") Long productId) {

    return shoppingCartService.getOrderLineByProductId(userId, productId);
  }

  @PostMapping(params = {"productId", "quantity"})
  public OrderLine addToCart(@PathVariable Long userId,
      @RequestParam(value = "productId") Long productId,
      @RequestParam(value = "quantity") int quantity) {

    return shoppingCartService.addToCart(userId, productId, quantity);
  }

  @DeleteMapping(params = "orderLineId")
  public boolean deleteFromCartByOrderLineId(@PathVariable Long userId,
      @RequestParam(value = "orderLineId") Long orderLineId) {

    return shoppingCartService.deleteFromCartByOrderLineId(userId, orderLineId);
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
