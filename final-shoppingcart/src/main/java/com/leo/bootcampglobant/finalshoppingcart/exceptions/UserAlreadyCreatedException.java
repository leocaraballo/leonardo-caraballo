package com.leo.bootcampglobant.finalshoppingcart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception threw when trying to create a new User with a username that is already in use.
 *
 * HTTP Status Code: 409 - Conflict
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyCreatedException extends RuntimeException {

  private static final String MESSAGE_FORMAT = "The user with the username %s already exist.";

  /**
   * Construct a UserAlreadyCreatedException with the message 'The user with the username
   * {@code username} already exist'.
   *
   * <p>Example: 'The user with the username {TheKing} already exist.'</p>
   *
   * @param username which is already in use.
   */
  public UserAlreadyCreatedException(String username) {
    super(String.format(MESSAGE_FORMAT, username));
  }

}
