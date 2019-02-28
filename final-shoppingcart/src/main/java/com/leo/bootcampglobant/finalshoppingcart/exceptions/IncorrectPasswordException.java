package com.leo.bootcampglobant.finalshoppingcart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception threw when a there's a password mismatch in the login
 *
 * HTTP Status Code: 400 - Bad Request
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectPasswordException extends RuntimeException {

  private static final String MESSAGE = "Incorrect Password";

  /**
   * Construct a IncorrectPasswordException with the message 'Incorrect Password'
   */
  public IncorrectPasswordException() {
    super(MESSAGE);
  }
}