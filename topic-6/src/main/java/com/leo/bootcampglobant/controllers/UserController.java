package com.leo.bootcampglobant.controllers;

import com.leo.bootcampglobant.models.User;
import com.leo.bootcampglobant.services.UserService;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UserController.BASE_URL)
public class UserController {

  public static final String BASE_URL = "/api/users";

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @GetMapping("/{id:[0-9]+}")
  public User getUserById(@PathVariable Long id) {
    return userService.getUserById(id);
  }

  @GetMapping("/{username:[a-zA-Z]+}")
  public User getUserByUsername(@PathVariable String username) {
    return userService.getUserByUsername(username);
  }

  @GetMapping(params = "firstName")
  public List<User> getUsersByFirstName(
      @RequestParam(value = "firstName") String firstName) {

    return userService.getUserByFirstName(firstName);
  }

  @GetMapping(params = "lastName")
  public List<User> getUsersByLastName(
      @RequestParam(value = "lastName") String lastName) {

    return userService.getUserByLastName(lastName);
  }

  @PostMapping
  public User createUser(@RequestBody User user) {
    return userService.createUser(user);
  }

  @PutMapping("/{id}")
  public User replaceUser(@RequestBody User user, @PathVariable Long id) {
    user.setId(id);
    return userService.replaceUser(user);
  }

  @DeleteMapping("/{id}")
  public boolean deleteUser(@PathVariable Long id) {
    return userService.deleteUserById(id);
  }

}
