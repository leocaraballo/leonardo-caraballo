package com.leo.bootcampglobant.controllers;

import com.leo.bootcampglobant.models.Product;
import com.leo.bootcampglobant.services.ProductService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ProductController.BASE_URL)
public class ProductController {

  public static final String BASE_URL = "/api/products";

  private final ProductService productService;

  @Autowired
  public ProductController(ProductService productService) {
    productService.newProduct(
        new Product("Smart TV 42'", "Electronics", new BigDecimal("149.99"))
    );
    productService.newProduct(
        new Product("Soda Cola 3L", "Grocery", new BigDecimal("14.49"))
    );
    productService.newProduct(
        new Product("New Phone X", "Electronics", new BigDecimal("449.99"))
    );
    productService.newProduct(
        new Product("Bread 1Kg", "Grocery", new BigDecimal("1.99"))
    );
    productService.newProduct(
        new Product("Generic Rap Songs", "Music", new BigDecimal("11.49"))
    );

    this.productService = productService;
  }

  @GetMapping
  public List<Product> getAllProducts() {
    return productService.getAllProducts();
  }

  @GetMapping("/{id}")
  public Product getProduct(@PathVariable long id) {
    return productService.getProduct(id);
  }

  @PostMapping
  public Product newProduct(@RequestBody Product product) {
    return productService.newProduct(product);
  }

  @PutMapping("/{id}")
  public Product replaceProduct(@RequestBody Product product, @PathVariable long id) {
    product.setId(id);
    return productService.replaceProduct(product);
  }

  @DeleteMapping("/{id}")
  public void deleteProduct(@PathVariable long id) {
    productService.deleteProductById(id);
  }
}
