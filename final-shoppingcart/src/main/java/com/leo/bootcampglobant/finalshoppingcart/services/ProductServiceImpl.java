package com.leo.bootcampglobant.finalshoppingcart.services;

import com.leo.bootcampglobant.finalshoppingcart.exceptions.AlreadyCreatedException;
import com.leo.bootcampglobant.finalshoppingcart.exceptions.CategoryNotFoundException;
import com.leo.bootcampglobant.finalshoppingcart.exceptions.ProductNotFoundException;
import com.leo.bootcampglobant.finalshoppingcart.exceptions.PropertyValidationException;
import com.leo.bootcampglobant.finalshoppingcart.models.Category;
import com.leo.bootcampglobant.finalshoppingcart.models.Product;
import com.leo.bootcampglobant.finalshoppingcart.repositories.CategoryRepository;
import com.leo.bootcampglobant.finalshoppingcart.repositories.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final CategoryRepository categoryRepository;

  public ProductServiceImpl(ProductRepository productRepository,
      CategoryRepository categoryRepository) {
    this.productRepository = productRepository;
    this.categoryRepository = categoryRepository;
  }

  @Override
  public Product getProductById(Long productId) {
    return productRepository.findById(productId)
        .orElseThrow(() -> new ProductNotFoundException(productId));
  }

  @Override
  public List<Product> getAllProducts() {
    return productRepository.findAll();
  }

  @Override
  public Category getCategoryById(Long categoryId) {
    return categoryRepository.findById(categoryId)
        .orElseThrow(() -> new CategoryNotFoundException(categoryId));
  }

  @Override
  public List<Category> getAllCategories() {
    return categoryRepository.findAll();
  }

  private static PropertyValidationException handleValidationExceptions(
      ConstraintViolationException e) {
    var validationInfo = new ArrayList<>(e.getConstraintViolations()).get(0);
    String field = validationInfo.getPropertyPath().toString();
    String message = validationInfo.getMessage();
    return new PropertyValidationException(field, message);
  }

  private Product saveProduct(Product product) {
    try {
      return productRepository.save(product);
    } catch (ConstraintViolationException e) {
      throw handleValidationExceptions(e);
    } catch (TransactionSystemException e) {
      throw handleValidationExceptions(
          (ConstraintViolationException) e.getOriginalException().getCause());
    } catch (DataIntegrityViolationException e) {
      throw new AlreadyCreatedException("Product", "name", product.getName());
    }
  }

  @Override
  public Product createProduct(Product product, Long categoryId) {
    Category category = getCategoryById(categoryId);
    product.setCategory(category);
    return saveProduct(product);
  }

  @Override
  public Category createCategory(Category category) {
    try {
      return categoryRepository.save(category);
    } catch (ConstraintViolationException e) {
      throw handleValidationExceptions(e);
    } catch (DataIntegrityViolationException e) {
      throw new AlreadyCreatedException("Category", "name", category.getName());
    }
  }

  @Override
  public Product replaceProduct(Product product) {
    return productRepository.findById(product.getId()).map(e -> {
      if (product.getName() != null) {
        e.setName(product.getName());
      }
      if (product.getPrice() != null) {
        e.setPrice(product.getPrice());
      }
      return this.saveProduct(e);
    }).orElseThrow(() -> new ProductNotFoundException(product.getId()));
  }

  @Override
  public Product replaceCategory(Long productId, Long categoryId) {
    Product product = getProductById(productId);
    Category category = getCategoryById(categoryId);

    product.setCategory(category);
    return productRepository.save(product);
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
