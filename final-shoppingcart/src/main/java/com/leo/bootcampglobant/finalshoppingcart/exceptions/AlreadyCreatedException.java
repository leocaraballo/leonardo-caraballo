package com.leo.bootcampglobant.finalshoppingcart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception threw when trying to create a new User with a username that is already in use.
 *
 * HTTP Status Code: 409 - Conflict
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class AlreadyCreatedException extends RuntimeException {

  private static final String MESSAGE_FORMAT = "The %s with the %s '%s' already exist.";

  /**
   * Construct a AlreadyCreatedException with the message 'The {@code entity} with {@code field}
   * {@code value} already exist'.
   *
   * <p>Example: 'The {user} with the {username} {TheKing} already exist.'</p>
   *
   * @param entity referred
   * @param field which must be unique
   * @param value which is already in use
   */
  public AlreadyCreatedException(String entity, String field, String value) {
    super(String.format(MESSAGE_FORMAT, entity, field, value));
  }

}
