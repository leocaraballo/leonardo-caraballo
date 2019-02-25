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

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public List<Product> getAllProducts() {
    return productService.getAllProducts();
  }

  @PostMapping
  public Product createProduct(@RequestBody Product product) {
    return productService.createProduct(product);
  }

  @GetMapping("/{productId}")
  public Product getProductById(@PathVariable Long productId) {
    return productService.getProductById(productId);
  }


  @PutMapping("/{productId}")
  public Product replaceProduct(@RequestBody Product product, @PathVariable Long productId) {
    product.setId(productId);
    return productService.replaceProduct(product);
  }

  @DeleteMapping("/{productId}")
  public boolean deleteProductById(@PathVariable Long productId) {
    return productService.deleteProductById(productId);
  }
}

