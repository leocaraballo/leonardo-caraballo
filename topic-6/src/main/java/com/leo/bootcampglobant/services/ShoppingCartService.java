package com.leo.bootcampglobant.services;

import com.leo.bootcampglobant.models.Order;
import com.leo.bootcampglobant.models.OrderLine;
import com.leo.bootcampglobant.models.Product;
import java.util.List;

public interface ShoppingCartService {

  Order getCurrentCart();
  List<Order> getOrders();
  OrderLine getOrderLine(long id);
  OrderLine addToCart(Product product, int quantity);
  void removeFromCart(long id);
  void removeFromCartByProductId(long id);
  void clear();
  Order checkOut();

}
