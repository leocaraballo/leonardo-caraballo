package com.leo.bootcampglobant.finalshoppingcart.services;

import com.leo.bootcampglobant.finalshoppingcart.models.Category;
import com.leo.bootcampglobant.finalshoppingcart.models.Product;
import java.util.List;

public interface ProductService {

  Product getProductById(Long productId);
  Product getProductByName(String productName);
  List<Product> getAllProducts();
  List<Product> getProductsByCategoryId(Long categoryId);
  List<Product> getProductsByCategoryName(String categoryName);
  List<Product> getProductsByWordInName(String productName);
  Category getCategoryById(Long categoryId);
  List<Category> getAllCategories();
  Product createProduct(Product product, Long categoryId);
  Product replaceProduct(Product product);
  Product replaceCategory(Long productId, Long categoryId);
  boolean deleteProductById(Long id);
  Category createCategory(Category category);

}
