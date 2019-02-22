package com.leo.bootcampglobant.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception threw when a search for an Order with a specific id fails.
 *
 * HTTP Status Code: 404 - Not Found
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends RuntimeException {

  private static final String MESSAGE_PRE = "Couldn't find the order with id ";

  /**
   * Construct a OrderNotFoundException with the message 'Couldn't find the order
   * with id {@code orderId}'.
   *
   * <p>Example: Couldn't find the order with id {5}</p>
   *
   * @param orderId id of the order which wasn't found.
   */
  public OrderNotFoundException(Long orderId) {
    super(MESSAGE_PRE + orderId);
  }
}
