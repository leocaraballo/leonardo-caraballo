package com.leo.bootcampglobant.finalshoppingcart.controllers;

import com.leo.bootcampglobant.finalshoppingcart.exceptions.PermissionDeniedException;
import com.leo.bootcampglobant.finalshoppingcart.models.Role;
import com.leo.bootcampglobant.finalshoppingcart.models.User;
import com.leo.bootcampglobant.finalshoppingcart.services.UserService;
import java.util.List;
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

  public UserController(UserService userService) {
    this.userService = userService;
  }

  private void onlyAdmin(Long token) {
    if (userService.authorization(token).getRole() != Role.ADMIN) {
      throw new PermissionDeniedException();
    }
  }

  private void verifyUser(Long token, Long userId) {
    try {
      onlyAdmin(token);
    } catch (Exception e) {
      if (!userService.authorization(token).getId().equals(userId)) {
        throw new PermissionDeniedException();
      }
    }
  }

  @GetMapping(params = "token")
  public List<User> getAllUsers(@RequestParam(value = "token") Long token) {

    onlyAdmin(token);
    return userService.getAllUsers();
  }

  @PostMapping
  public User createUser(@RequestBody User user) {
    user.setRole(Role.NORMAL);
    return userService.createUser(user);
  }

  @GetMapping(value = "/login", params = {"username", "password"})
  public User login(@RequestParam(value = "username") String username,
      @RequestParam(value = "password") String password) {

    return userService.login(username, password);
  }

  @GetMapping(params = {"token", "lastName"})
  public List<User> getUsersByLastName(
      @RequestParam(value = "token") Long token,
      @RequestParam(value = "lastName") String lastName) {

    onlyAdmin(token);
    return userService.getUserByLastName(lastName);
  }

  @GetMapping(params = {"token", "firstName"})
  public List<User> getUsersByFirstName(
      @RequestParam(value = "token") Long token,
      @RequestParam(value = "firstName") String firstName) {

    onlyAdmin(token);
    return userService.getUserByFirstName(firstName);
  }

  @GetMapping(value = "/{username:[a-zA-Z]+}", params = "token")
  public User getUserByUsername(@RequestParam(value = "token") Long token,
      @PathVariable String username) {

    onlyAdmin(token);
    return userService.getUserByUsername(username);
  }

  @GetMapping(value = "/{id:[0-9]+}", params = "token")
  public User getUserById(@RequestParam(value = "token") Long token,
      @PathVariable Long id) {

    onlyAdmin(token);
    return userService.getUserById(id);
  }

  @PutMapping(value = "/{id}", params = "token")
  public User replaceUser(@RequestParam(value = "token") Long token,
      @RequestBody User user, @PathVariable Long id) {

    verifyUser(token, id);
    user.setId(id);
    return userService.replaceUser(user);
  }

  @DeleteMapping(value = "/{id}", params = "token")
  public boolean deleteUser(@RequestParam(value = "token") Long token,
      @PathVariable Long id) {

    verifyUser(token, id);
    return userService.deleteUserById(id);
  }

}
