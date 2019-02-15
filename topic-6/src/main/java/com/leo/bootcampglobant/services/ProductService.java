package com.leo.bootcampglobant.services;

import com.leo.bootcampglobant.models.Product;
import java.util.List;

public interface ProductService {

  Product getProduct(long id);
  List<Product> getAllProducts();
  List<Product> getProductsByCategory(String category);
  Product newProduct(Product p);
  Product replaceProduct(Product p);
  void deleteProduct(Product p);
  void deleteProductById(long id);

}
