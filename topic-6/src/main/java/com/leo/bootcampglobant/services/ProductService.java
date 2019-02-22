package com.leo.bootcampglobant.services;

import com.leo.bootcampglobant.models.Product;
import java.util.List;

public interface ProductService {

  Product getProductById(Long id);
  List<Product> getAllProducts();
  Product createProduct(Product product);
  Product replaceProduct(Product product);
  boolean deleteProductById(Long id);

}
