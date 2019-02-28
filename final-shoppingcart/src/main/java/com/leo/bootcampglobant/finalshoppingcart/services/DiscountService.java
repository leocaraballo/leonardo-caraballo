package com.leo.bootcampglobant.finalshoppingcart.services;

import com.leo.bootcampglobant.finalshoppingcart.models.Discount;
import com.leo.bootcampglobant.finalshoppingcart.models.Product;
import java.util.List;

public interface DiscountService {

  Discount getDiscountById(Long discountId);
  List<Product> addDiscountByCategory(Discount discount, Long categoryId);
  List<Product> addDiscountByWord(Discount discount, String containingWord);
  Product addDiscountById(Discount discount, Long productId);
  Product clearDiscountsFromProduct(Long productId);
  Product removeDiscountFromProduct(Long discountId, Long productId);
  List<Product> removeDiscount(Long discountId);

}
