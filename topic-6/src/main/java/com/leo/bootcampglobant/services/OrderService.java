package com.leo.bootcampglobant.services;

import com.leo.bootcampglobant.models.Order;
import java.util.List;

public interface OrderService {

  Order getOrderById(Long userId, Long orderId);
  List<Order> getUserOrders(Long userId);

}
