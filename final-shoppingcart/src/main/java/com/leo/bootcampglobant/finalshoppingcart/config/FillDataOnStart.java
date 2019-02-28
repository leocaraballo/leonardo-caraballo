package com.leo.bootcampglobant.finalshoppingcart.config;

import com.leo.bootcampglobant.finalshoppingcart.models.Category;
import com.leo.bootcampglobant.finalshoppingcart.models.Product;
import com.leo.bootcampglobant.finalshoppingcart.models.User;
import com.leo.bootcampglobant.finalshoppingcart.services.ProductService;
import com.leo.bootcampglobant.finalshoppingcart.services.ShoppingCartService;
import com.leo.bootcampglobant.finalshoppingcart.services.UserService;
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
    productService.createCategory(new Category("Commons", "Default Category for all the items"));
    productService
        .createCategory(new Category("Electronics", "Home appliances or digital devices"));
    productService.createCategory(new Category("Groceries", "Foods and drinks"));
    productService.createCategory(new Category("Music", "Everything related to music"));

    productService.createProduct(
        new Product("Smart TV 42'", new BigDecimal("149.99")),
        2L
    );
    productService.createProduct(
        new Product("Soda Cola 3L", new BigDecimal("14.49")),
        3L
    );
    productService.createProduct(
        new Product("New Phone X", new BigDecimal("449.99")),
        2L
    );
    productService.createProduct(
        new Product("Bread 1Kg", new BigDecimal("1.99")),
        3L
    );
    productService.createProduct(
        new Product("Generic Rap Songs", new BigDecimal("11.49")),
        4L
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
