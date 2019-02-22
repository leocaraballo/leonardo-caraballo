package com.leo.bootcampglobant.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception threw when a search for an User with a specific field/property fails.
 *
 * HTTP Status Code: 404 - Not Found
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

  private static final String MESSAGE_PRE = "Couldn't find the user with the ";

  /**
   * Construct a UserNotFoundException with the message 'Couldn't find the user with the
   * {@code message}', where {@code message} should be the name of the property which the search
   * failed on, and his value.
   *
   * <p>Example: 'Couldn't find the user with the {id of 1}'</p>
   *
   * @param message property with which the user was searched, and its respective value.
   */
  public UserNotFoundException(String message) {
    super(MESSAGE_PRE + message);
  }

}
