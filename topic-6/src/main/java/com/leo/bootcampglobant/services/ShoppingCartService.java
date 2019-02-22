package com.leo.bootcampglobant.services;

import com.leo.bootcampglobant.models.Order;
import com.leo.bootcampglobant.models.OrderLine;
import com.leo.bootcampglobant.models.ShoppingCart;

public interface ShoppingCartService {

  ShoppingCart getShopingCartByUserId(Long userId);
  OrderLine getOrderLineById(Long userId, Long orderLineId);
  OrderLine getOrderLineByProductId(Long userId, Long productId);
  OrderLine addToCart(Long userId, Long productId, int quantity);
  boolean deleteFromCartByOrderLineId(Long userId, Long orderLineId);
  boolean deleteFromCartByProductId(Long userId, Long productId);
  void clear(Long userId);
  Order checkOut(Long userId);

}
