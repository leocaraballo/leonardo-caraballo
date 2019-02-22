package com.leo.bootcampglobant.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.leo.bootcampglobant.exceptions.ProductNotFoundException;
import com.leo.bootcampglobant.models.Product;
import com.leo.bootcampglobant.repositories.ProductRepository;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ProductServiceImplTest {

  @InjectMocks
  private ProductServiceImpl productService;
  @Mock
  private ProductRepository mockedProductRepository;

  private static final List<Product> testProducts = Arrays.asList(
      new Product(1L, "Test product 1", new BigDecimal("399.99")),
      new Product(2L, "Test product 2", new BigDecimal("249.99")),
      new Product(3L, "Test product 3", new BigDecimal("175.00"))
  );


  @Before
  public void initialize() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void getProductById_correctRetrieve() {
    when(mockedProductRepository.findById(anyLong())).thenReturn(Optional.of(testProducts.get(0)));

    assertSame(testProducts.get(0), productService.getProductById(1L));
    verify(mockedProductRepository).findById(1L);
  }

  @Test(expected = ProductNotFoundException.class)
  public void getProductById_productNotFound() {
    when(mockedProductRepository.findById(anyLong())).thenReturn(Optional.empty());

    try {
      productService.getProductById(1L);
    } finally {
      verify(mockedProductRepository).findById(1L);
    }
  }

  @Test
  public void getAllProducts_correctRetrieve() {
    when(mockedProductRepository.findAll()).thenReturn(testProducts);

    assertEquals(testProducts, productService.getAllProducts());
    verify(mockedProductRepository).findAll();
  }

  @Test
  public void getAllProducts_empty() {
    when(mockedProductRepository.findAll()).thenReturn(Collections.emptyList());

    assertEquals(Collections.emptyList(), productService.getAllProducts());
    verify(mockedProductRepository).findAll();
  }

  @Test
  public void createProduct_correctInsert() {
    when(mockedProductRepository.save(any())).then(AdditionalAnswers.returnsFirstArg());

    assertSame(testProducts.get(0), productService.createProduct(testProducts.get(0)));
    verify(mockedProductRepository).save(testProducts.get(0));
  }

  @Test
  public void replaceProduct_correctUpdate() {
    Product updatedProduct = new Product(1L, "First test product", new BigDecimal("2.99"));

    when(mockedProductRepository.findById(1L)).thenReturn(Optional.of(testProducts.get(0)));
    when(mockedProductRepository.save(any())).then(AdditionalAnswers.returnsFirstArg());

    assertEquals(updatedProduct, productService.replaceProduct(updatedProduct));
    verify(mockedProductRepository).save(updatedProduct);
  }

  @Test(expected = ProductNotFoundException.class)
  public void replaceProduct_productNotFound() {
    Product updatedProduct = new Product(1L, "First test product", new BigDecimal("2.99"));

    when(mockedProductRepository.findById(1L)).thenReturn(Optional.empty());

    try {
      productService.replaceProduct(updatedProduct);
    } finally {
      verify(mockedProductRepository, never()).save(any());
    }
  }

  @Test
  public void deleteProductById_correctDeletion() {
    assertTrue(productService.deleteProductById(1L));
    verify(mockedProductRepository).deleteById(1L);
  }

  @Test
  public void deleteProductById_productNotFound() {
    doThrow(RuntimeException.class).when(mockedProductRepository).deleteById(any());
    assertFalse(productService.deleteProductById(1L));
    verify(mockedProductRepository).deleteById(1L);
  }
}