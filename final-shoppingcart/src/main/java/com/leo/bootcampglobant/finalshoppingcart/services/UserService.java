package com.leo.bootcampglobant.finalshoppingcart.services;

import com.leo.bootcampglobant.finalshoppingcart.models.Order;
import com.leo.bootcampglobant.finalshoppingcart.models.User;
import java.util.List;

public interface UserService {

  User getUserById(Long id);
  User getUserByUsername(String username);
  List<User> getUserByFirstName(String firstName);
  List<User> getUserByLastName(String lastName);
  List<User> getAllUsers();
  User createUser(User user);
  User replaceUser(User user);
  boolean deleteUserById(Long id);
  Order createOrder(Long userId, Order order);
  User login(String username, String password);
  User authorization(Long token);
}
