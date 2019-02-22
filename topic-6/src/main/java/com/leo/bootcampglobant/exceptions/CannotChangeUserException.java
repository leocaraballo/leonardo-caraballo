package com.leo.bootcampglobant.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception threw when an update tries to change a (forbidden to change) User field/property.
 *
 * HTTP Status Code: 400 - Bad Request
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CannotChangeUserException extends RuntimeException {

  private static final String MESSAGE_FORMAT = "Can't change the %s property of a user.";

  /**
   * Construct a CannotChangeUserException with the message 'Can't change the {@code propertyName}
   * property of a user.'.
   *
   * <p>Example: 'Can't change the {username} property of a user.'</p>
   *
   * @param propertyName name of the field/property which can't be changed.
   */
  public CannotChangeUserException(String propertyName) {
    super(String.format(MESSAGE_FORMAT, propertyName));
  }
}
