package com.leo.bootcampglobant.finalshoppingcart.services;

import com.leo.bootcampglobant.finalshoppingcart.models.Order;
import java.util.List;

public interface OrderService {

  Order getOrderById(Long userId, Long orderId);
  List<Order> getUserOrders(Long userId);

}
