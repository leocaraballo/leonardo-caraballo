package com.leo.bootcampglobant.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception threw when a search for a OrderLine with a specific id fails.
 *
 * HTTP Status Code: 404 - Not Found
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderLineNotFoundException extends RuntimeException {

  private static final String MESSAGE_PRE = "Couldn't find the order line with ";

  /**
   * Construct a OrderLineNotFoundException with the message 'Couldn't find the order line with
   * the {@code message}', where {@code message} should be the name of the property which the
   * search failed on, and his value.
   *
   * <p>Example: 'Couldn't find the order line with the {id of 5}'</p>
   *
   * @param message value of the id of the order line not found.
   */
  public OrderLineNotFoundException(String message) {
    super(MESSAGE_PRE + message);
  }

}
