package com.leo.bootcampglobant.finalshoppingcart.controllers;

import com.leo.bootcampglobant.finalshoppingcart.models.Category;
import com.leo.bootcampglobant.finalshoppingcart.models.Product;
import com.leo.bootcampglobant.finalshoppingcart.services.ProductService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  @GetMapping("/categories")
  public List<Category> getAllCategories() {
    return productService.getAllCategories();
  }

  @PostMapping("/categories")
  public Category createCategory(@RequestBody Category category) {
    return productService.createCategory(category);
  }

  @PostMapping(params = "categoryId")
  public Product createProduct(@RequestBody Product product,
      @RequestParam(name = "categoryId") Long categoryId) {
    return productService.createProduct(product, categoryId);
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

  @PutMapping(value = "/{productId}/category", params = "categoryId")
  public Product replaceCategory(@PathVariable Long productId,
      @RequestParam(name = "categoryId") Long categoryId) {

    return productService.replaceCategory(productId, categoryId);
  }

  @DeleteMapping("/{productId}")
  public boolean deleteProductById(@PathVariable Long productId) {
    return productService.deleteProductById(productId);
  }
}

