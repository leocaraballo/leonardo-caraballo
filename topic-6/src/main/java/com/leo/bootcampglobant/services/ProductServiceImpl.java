package com.leo.bootcampglobant.services;

import com.leo.bootcampglobant.exceptions.ProductNotFoundException;
import com.leo.bootcampglobant.models.Product;
import com.leo.bootcampglobant.repositories.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @Autowired
  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public Product getProductById(Long id) {
    return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
  }

  @Override
  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  @Override
  public Product createProduct(Product product) {
    return productRepository.save(product);
  }

  @Override
  public Product replaceProduct(Product product) {
    return productRepository.findById(product.getId()).map(e -> productRepository.save(product))
        .orElseThrow(() -> new ProductNotFoundException(product.getId()));
  }

  @Override
  public boolean deleteProductById(Long id) {
    try {
      productRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
