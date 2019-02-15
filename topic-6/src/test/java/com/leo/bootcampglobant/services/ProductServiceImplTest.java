package com.leo.bootcampglobant.services;

import static org.junit.Assert.assertEquals;

import com.leo.bootcampglobant.models.Product;
import com.leo.bootcampglobant.repositories.ProductRepository;
import com.leo.bootcampglobant.repositories.RepositoryInMemory;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;

public class ProductServiceImplTest {

  private class FakeProductRepository
      extends RepositoryInMemory<Product>
      implements ProductRepository {

  }

  private static final Product[] testProducts = {
      new Product( "Smart TV 42'", "Electronics", new BigDecimal("149.99")),
      new Product("Soda Cola 3L", "Grocery", new BigDecimal("14.49")),
      new Product("New Phone X", "Electronics", new BigDecimal("459.99")),
      new Product("Bread 1Kg", "Grocery", new BigDecimal("1.99")),
      new Product("Generic Rap Songs", "Music", new BigDecimal("11.49"))
  };

  private ProductServiceImpl productService;

  @Before
  public void initialize() {
    FakeProductRepository productRepository = new FakeProductRepository();

    for (int i = 0; i < testProducts.length; ++i) {
      testProducts[i] = productRepository.create(testProducts[i]);
    }

    productService = new ProductServiceImpl(productRepository);
  }

  @Test
  public void getProduct_exist() {
    assertEquals(testProducts[0], productService.getProduct(testProducts[0].getId()));
  }

  @Test(expected = NoSuchElementException.class)
  public void getProduct_nonExisting() {
    // With 5 insertions, no id can be 42.
    productService.getProduct(42);
  }

  @Test
  public void getAllProducts_correct() {
    assertEquals(Arrays.asList(testProducts), productService.getAllProducts());
  }

  @Test
  public void getProductsByCategory_existingCategory() {
    assertEquals(Arrays.asList(testProducts[0], testProducts[2]),
        productService.getProductsByCategory("Electronics"));
  }


  @Test
  public void getProductsByCategory_nonExistingCategory() {
    assertEquals(Collections.emptyList(), productService.getProductsByCategory("Gaming"));
  }

  @Test
  public void newProduct_insertion() {
    Product newProduct = new Product("Game Station X", "Gaming", new BigDecimal("349.99"));
    newProduct = productService.newProduct(newProduct);
    assertEquals(newProduct, productService.getProduct(newProduct.getId()));
  }

  @Test
  public void replaceProduct_modify() {
    Product modifiedProduct = new Product(
        testProducts[0].getId(), "LED TV 30'",
        "Television", new BigDecimal("74.99")
    );

    productService.replaceProduct(modifiedProduct);
    assertEquals(modifiedProduct, productService.getProduct(modifiedProduct.getId()));
  }

  @Test(expected = NoSuchElementException.class)
  public void deleteProduct_objectDeletion() {
    productService.deleteProduct(testProducts[0]);
    productService.getProduct(testProducts[0].getId());
  }

  @Test(expected = NoSuchElementException.class)
  public void deleteProductById_idDeletion() {
    productService.deleteProductById(testProducts[0].getId());
    productService.getProduct(testProducts[0].getId());
  }

}