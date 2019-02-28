package com.leo.bootcampglobant.finalshoppingcart.services;

import com.leo.bootcampglobant.finalshoppingcart.exceptions.DiscountNotFoundException;
import com.leo.bootcampglobant.finalshoppingcart.models.Discount;
import com.leo.bootcampglobant.finalshoppingcart.models.Product;
import com.leo.bootcampglobant.finalshoppingcart.repositories.DiscountRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements DiscountService {

  private final DiscountRepository discountRepository;
  private final ProductService productService;

  public DiscountServiceImpl(
      DiscountRepository discountRepository,
      ProductService productService) {

    this.discountRepository = discountRepository;
    this.productService = productService;
  }

  @Override
  public Discount getDiscountById(Long discountId) {
    return discountRepository.findById(discountId)
        .orElseThrow(() -> new DiscountNotFoundException(discountId));
  }

  @Override
  public List<Product> addDiscountByCategory(Discount discount, Long categoryId) {
    Discount finalDiscount = discountRepository.save(discount);
    List<Product> affectedProducts = productService.getProductsByCategoryId(categoryId);
    affectedProducts.forEach(e -> {
      e.getDiscounts().add(finalDiscount);
    });
    return productService.persist(affectedProducts);
  }

  @Override
  public List<Product> addDiscountByWord(Discount discount, String containingWord) {
    Discount finalDiscount = discountRepository.save(discount);
    List<Product> affectedProducts = productService.getProductsByWordInName(containingWord);
    affectedProducts.forEach(e -> {
      e.getDiscounts().add(finalDiscount);
    });
    return productService.persist(affectedProducts);
  }

  @Override
  public Product addDiscountById(Discount discount, Long productId) {
    Product product = productService.getProductById(productId);
    product.getDiscounts().add(discountRepository.save(discount));
    return productService.saveProduct(product);
  }

  @Override
  public Product clearDiscountsFromProduct(Long productId) {
    Product product = productService.getProductById(productId);
    product.getDiscounts().clear();
    return productService.saveProduct(product);
  }

  @Override
  public Product removeDiscountFromProduct(Long discountId, Long productId) {
    Discount discount = getDiscountById(discountId);
    Product product = productService.getProductById(productId);
    product.getDiscounts().remove(discount);
    return productService.saveProduct(product);
  }

  @Override
  public List<Product> removeDiscount(Long discountId) {
    Discount discount = getDiscountById(discountId);
    List<Product> products = productService.getAllProducts();
    List<Product> affectedProducts = new ArrayList<>();

    products.forEach(e -> {
      if (e.getDiscounts().remove(discount)) {
        affectedProducts.add(e);
      }
    });
    discountRepository.delete(discount);

    return productService.persist(affectedProducts);
  }
}
