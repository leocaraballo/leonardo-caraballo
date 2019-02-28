package com.leo.bootcampglobant.finalshoppingcart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception threw when a search for a Discount with a specific id fails.
 *
 * HTTP Status Code: 404 - Not Found
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class DiscountNotFoundException extends RuntimeException {

  private static final String MESSAGE_PRE = "Couldn't find the discount with the id ";

  /**
   * Construct a DiscountNotFoundException with the message 'Couldn't find the discount with
   * the id {@code discountId}'
   *
   * <p>Example: 'Couldn't find the discount with the id {5}'</p>
   *
   * @param discountId value of the id of the discount not found.
   */
  public DiscountNotFoundException(Long discountId) {
    super(MESSAGE_PRE + discountId);
  }

}