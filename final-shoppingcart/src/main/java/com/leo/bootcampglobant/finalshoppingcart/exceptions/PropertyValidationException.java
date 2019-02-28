package com.leo.bootcampglobant.finalshoppingcart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception threw when a field/property of a newly created entity doesn't  fulfill the
 * validations.
 *
 * HTTP Status Code: 400 -  Bad Request
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PropertyValidationException extends RuntimeException {

  private static final String MESSAGE_FORMAT = "The field %s %s";
  /**
   * Construct a PropertyValidationException with a custom {@code message}, which should indicate which
   * field/property failed the validation, and why it failed.
   *
   * @param field which doesn't complies the validation
   * @param message to be showed on the response status error message.
   */
  public PropertyValidationException(String field, String message) {
    super(String.format(MESSAGE_FORMAT, field, message));
  }

}
