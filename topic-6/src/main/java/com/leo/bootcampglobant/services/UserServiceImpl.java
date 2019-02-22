package com.leo.bootcampglobant.services;

import com.leo.bootcampglobant.exceptions.CannotChangeUserException;
import com.leo.bootcampglobant.exceptions.PropertyValidationException;
import com.leo.bootcampglobant.exceptions.UserAlreadyCreatedException;
import com.leo.bootcampglobant.exceptions.UserNotFoundException;
import com.leo.bootcampglobant.models.Order;
import com.leo.bootcampglobant.models.ShoppingCart;
import com.leo.bootcampglobant.models.User;
import com.leo.bootcampglobant.repositories.OrderLineRepository;
import com.leo.bootcampglobant.repositories.OrderRepository;
import com.leo.bootcampglobant.repositories.ShoppingCartRepository;
import com.leo.bootcampglobant.repositories.UserRepository;
import java.util.List;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final OrderRepository orderRepository;
  private final ShoppingCartRepository shoppingCartRepository;
  private final OrderLineRepository orderLineRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository,
      OrderRepository orderRepository,
      ShoppingCartRepository shoppingCartRepository,
      OrderLineRepository orderLineRepository) {
    this.userRepository = userRepository;
    this.orderRepository = orderRepository;
    this.shoppingCartRepository = shoppingCartRepository;
    this.orderLineRepository = orderLineRepository;
  }

  @Override
  public User getUserById(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("id of " + id));
  }

  @Override
  public User getUserByUsername(String username) {
    return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(
        "username of " + username));
  }

  @Override
  public List<User> getUserByFirstName(String firstName) {
    return userRepository.findByFirstName(firstName);
  }

  @Override
  public List<User> getUserByLastName(String lastName) {
    return userRepository.findByLastName(lastName);
  }

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public User createUser(User user) {
    try {
      ShoppingCart shoppingCart = new ShoppingCart(user);
      shoppingCartRepository.save(shoppingCart);
      user = userRepository.save(user);
      return user;
    } catch (ConstraintViolationException e) {
      throw new PropertyValidationException(
          "The username MUST ONLY CONTAIN alphabetic characters (a-zA-Z).");
    } catch (Exception e) {
      e.printStackTrace();
      throw new UserAlreadyCreatedException(user.getUsername());
    }
  }

  @Override
  public User replaceUser(User user) {
    if (user.getUsername() != null) {
      throw new CannotChangeUserException("username");
    }

    return userRepository.findById(user.getId()).map(e -> {
      e.setFirstName(user.getFirstName());
      e.setLastName(user.getLastName());
      e.setPassword(user.getPassword());
      return userRepository.save(e);
    }).orElseThrow(() -> new UserNotFoundException("id of " + user.getId()));
  }

  @Override
  public boolean deleteUserById(Long id) {
    try {
      userRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      throw new UserNotFoundException("id of " + id);
    }
  }

  @Override
  public Order createOrder(Long userId, Order order) {
    User user = getUserById(userId);
    order.setItems(orderLineRepository.saveAll(order.getItems()));
    order = orderRepository.save(order);
    user.getOrders().add(order);
    userRepository.save(user);
    return order;
  }
}