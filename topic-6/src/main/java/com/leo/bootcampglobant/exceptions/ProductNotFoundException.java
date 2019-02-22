package com.leo.bootcampglobant.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception threw when a search for a Product with a specific id fails.
 *
 * HTTP Status Code: 404 - Not Found
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

  private static final String MESSAGE_PRE = "Couldn't find the product with the id ";

  /**
   * Construct a ProductNotFoundException with the message 'Couldn't find the product with
   * the id {@code id}', where {@code id} is the value.
   *
   * <p>Example: 'Couldn't find the product with the id {5}'</p>
   *
   * @param id value of the id of the product not found.
   */
  public ProductNotFoundException(Long id) {
    super(MESSAGE_PRE + id);
  }

}
