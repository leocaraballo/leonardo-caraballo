package com.leo.bootcampglobant.finalshoppingcart.controllers;

import com.leo.bootcampglobant.finalshoppingcart.models.Discount;
import com.leo.bootcampglobant.finalshoppingcart.models.Product;
import com.leo.bootcampglobant.finalshoppingcart.services.DiscountService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(DiscountController.BASE_URL)
public class DiscountController {

  public static final String BASE_URL = "/api/discounts";

  private final DiscountService discountService;

  public DiscountController(
      DiscountService discountService) {

    this.discountService = discountService;
  }

  @GetMapping("/{discountId}")
  public Discount getDiscountById(@PathVariable Long discountId) {
    return discountService.getDiscountById(discountId);
  }

  @PostMapping(params = "categoryId")
  public List<Product> addDiscountByCategory(@RequestBody Discount discount,
      @RequestParam(name = "categoryId") Long categoryId) {

    return discountService.addDiscountByCategory(discount, categoryId);
  }

  @PostMapping(params = "containingWord")
  public List<Product> addDiscountByWord(@RequestBody Discount discount,
      @RequestParam(name = "containingWord") String containingWord) {

    return discountService.addDiscountByWord(discount, containingWord);
  }

  @PostMapping(params = "productId")
  public Product addDiscountByProductId(@RequestBody Discount discount,
      @RequestParam(name = "productId") Long productId) {

    return discountService.addDiscountById(discount, productId);
  }

  @PatchMapping(value = "/clear", params = "productId")
  public Product clearDiscountsFromProduct(@RequestParam(name = "productId") Long productId) {
    return discountService.clearDiscountsFromProduct(productId);
  }

  @DeleteMapping(value = "/{discountId}", params = "productId")
  public Product removeDiscountFromProduct(@PathVariable Long discountId,
      @RequestParam(name = "productId") Long productId) {

    return discountService.removeDiscountFromProduct(discountId, productId);
  }

  @DeleteMapping("/{discountId}")
  public List<Product> deleteDiscount(@PathVariable Long discountId) {
    return discountService.removeDiscount(discountId);
  }

}
