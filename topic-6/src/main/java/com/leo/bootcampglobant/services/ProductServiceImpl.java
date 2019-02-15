package com.leo.bootcampglobant.services;

import com.leo.bootcampglobant.models.Product;
import com.leo.bootcampglobant.repositories.ProductRepository;
import com.leo.bootcampglobant.repositories.ProductRepositoryInMemory;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public ProductServiceImpl() {
    this(new ProductRepositoryInMemory());
  }

  @Override
  public Product getProduct(long id) {
    return productRepository.readByKey(id).get();
  }

  @Override
  public List<Product> getAllProducts() {
    return productRepository.readAll();
  }

  @Override
  public List<Product> getProductsByCategory(String category) {
    return productRepository.readAll().stream().filter(e -> e.getCategory().equals(category)).
        collect(Collectors.toUnmodifiableList());
  }

  @Override
  public Product newProduct(Product p) {
    return productRepository.create(p);
  }

  @Override
  public Product replaceProduct(Product p) {
    return productRepository.update(p);
  }

  @Override
  public void deleteProduct(Product p) {
    productRepository.delete(p);
  }

  @Override
  public void deleteProductById(long id) {
    productRepository.deleteByKey(id);
  }
}
