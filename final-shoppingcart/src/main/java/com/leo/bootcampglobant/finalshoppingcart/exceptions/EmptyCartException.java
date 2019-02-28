package com.leo.bootcampglobant.finalshoppingcart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception threw when someone try to check out and produce a Order from a empty ShoppingCart
 *
 * HTTP Status Code: 400 - Bad Request
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmptyCartException extends RuntimeException {

  private static final String MESSAGE = "Can't check out a empty shopping cart.";

  /**
   * Construct a EmptyCartException with the message 'Can't check out a empty shopping cart.'.
   */
  public EmptyCartException() {
    super(MESSAGE);
  }

}
