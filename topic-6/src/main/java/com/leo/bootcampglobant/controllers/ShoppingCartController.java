package com.leo.bootcampglobant.controllers;

import com.leo.bootcampglobant.models.Order;
import com.leo.bootcampglobant.models.OrderLine;
import com.leo.bootcampglobant.models.Product;
import com.leo.bootcampglobant.services.ProductService;
import com.leo.bootcampglobant.services.ShoppingCartService;
import com.leo.bootcampglobant.services.ShoppingCartServiceImpl;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ShoppingCartController.BASE_URL)
public class ShoppingCartController {

  public static final String BASE_URL = "/api/shoppingcart";

  private ShoppingCartService shoppingCartService;

  @Autowired
  public ShoppingCartController(ProductService productService) {
    this.shoppingCartService = new ShoppingCartServiceImpl(productService);

    for (Product p : productService.getAllProducts()) {
      this.shoppingCartService.addToCart(p, 2);
    }
  }

  @GetMapping
  public Order getCurrentCart() {
    return shoppingCartService.getCurrentCart();
  }

  @GetMapping("/current/{id}")
  public OrderLine getOrderLine(@PathVariable long id) {
    return shoppingCartService.getOrderLine(id);
  }

  @GetMapping("/orders")
  public List<Order> getOrders() {
    return shoppingCartService.getOrders();
  }

  @PatchMapping
  public OrderLine addToCart(@RequestBody Product product,
                             @RequestParam(value = "quantity") int quantity) {

    OrderLine orderLine = null;

    try {
      orderLine = shoppingCartService.addToCart(product, quantity);
    } catch (NoSuchElementException e) {
      throw new RuntimeException("The product " + product + " doesn't exist.");
    }

    return orderLine;
  }

  @DeleteMapping("/{id}")
  public void removeFromCart(@PathVariable long id) {
    shoppingCartService.removeFromCart(id);
  }

  @DeleteMapping
  public void removeFromCartByProductId(@RequestParam(value = "productId") long productId) {
    shoppingCartService.removeFromCartByProductId(productId);
  }

  @DeleteMapping("/clear")
  public void clearCart() {
    shoppingCartService.clear();
  }

  @PostMapping("/checkout")
  public Order checkOut() {
    return shoppingCartService.checkOut();
  }

}
