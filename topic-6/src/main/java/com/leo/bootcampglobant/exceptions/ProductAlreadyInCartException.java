package com.leo.bootcampglobant.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception threw when someone tries to add a Product to a ShoppingCart which already
 * contains that product.
 *
 * HTTP Status Code: 409 - Conflict
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class ProductAlreadyInCartException extends RuntimeException {

  private static final String MESSAGE = "There is already a product with the id %d in "
                                          + "the cart.";

  /**
   * Construct a ProductAlreadyInTheCartException with the message ''There is already a product
   * with the id {@code productId} in the cart'.
   *
   * <p>Example: 'There is already a product with the id {2} in the cart'</p>
   *
   * @param productId id of the product which is already in the cart.
   */
  public ProductAlreadyInCartException(Long productId) {
    super(String.format(MESSAGE, productId));
  }
}
