package com.leo.bootcampglobant.finalshoppingcart.services;

import com.leo.bootcampglobant.finalshoppingcart.models.CartItem;
import com.leo.bootcampglobant.finalshoppingcart.models.Order;
import com.leo.bootcampglobant.finalshoppingcart.models.ShoppingCart;

public interface ShoppingCartService {

  ShoppingCart getShopingCartByUserId(Long userId);
  CartItem getCartItemById(Long userId, Long cartItemId);
  CartItem getCartItemByProductId(Long userId, Long productId);
  CartItem addToCart(Long userId, Long productId, int quantity);
  boolean deleteFromCartByCartItemId(Long userId, Long cartItemId);
  boolean deleteFromCartByProductId(Long userId, Long productId);
  void clear(Long userId);
  Order checkOut(Long userId);

}
