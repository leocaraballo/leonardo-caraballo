package com.leo.bootcampglobant.services;

import com.leo.bootcampglobant.exceptions.OrderNotFoundException;
import com.leo.bootcampglobant.models.Order;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

  private final UserService userService;

  @Autowired
  public OrderServiceImpl(UserService userService) {
    this.userService = userService;
  }

  @Override
  public Order getOrderById(Long userId, Long orderId) {
    return userService.getUserById(userId).getOrders().stream()
        .filter(e -> e.getId().equals(orderId)).findFirst().orElseThrow(() ->
            new OrderNotFoundException(orderId));
  }

  @Override
  public List<Order> getUserOrders(Long userId) {
    return userService.getUserById(userId).getOrders();
  }
}
