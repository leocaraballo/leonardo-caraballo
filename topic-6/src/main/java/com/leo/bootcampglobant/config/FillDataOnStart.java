package com.leo.bootcampglobant.config;

import com.leo.bootcampglobant.models.Product;
import com.leo.bootcampglobant.models.User;
import com.leo.bootcampglobant.services.ProductService;
import com.leo.bootcampglobant.services.ShoppingCartService;
import com.leo.bootcampglobant.services.UserService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class FillDataOnStart implements CommandLineRunner {

  private final ProductService productService;
  private final UserService userService;
  private final ShoppingCartService shoppingCartService;

  @Autowired
  public FillDataOnStart(ProductService productService,
      UserService userService,
      ShoppingCartService shoppingCartService) {

    this.productService = productService;
    this.userService = userService;
    this.shoppingCartService = shoppingCartService;
  }

  @Override
  public void run(String... args) throws Exception {
    productService.createProduct(
        new Product("Smart TV 42'", new BigDecimal("149.99"))
    );
    productService.createProduct(
        new Product("Soda Cola 3L", new BigDecimal("14.49"))
    );
    productService.createProduct(
        new Product("New Phone X", new BigDecimal("449.99"))
    );
    productService.createProduct(
        new Product("Bread 1Kg", new BigDecimal("1.99"))
    );
    productService.createProduct(
        new Product("Generic Rap Songs", new BigDecimal("11.49"))
    );

    userService.createUser(
        new User("Eamon", "Andrew", "eamondrew", "password")
    );
    userService.createUser(
        new User("Celine", "Monaghan", "celinee", "asdf1234")
    );
    userService.createUser(
        new User("Ivor", "Andrew", "theandrews", "random64")
    );
    userService.createUser(
        new User("Celine", "Fitzgerald", "fitzboss", "7jkj78f_s2")
    );

    shoppingCartService.addToCart(1L, 1L, 2);
    shoppingCartService.addToCart(1L, 2L, 5);
    shoppingCartService.addToCart(1L, 3L, 1);
  }
}
