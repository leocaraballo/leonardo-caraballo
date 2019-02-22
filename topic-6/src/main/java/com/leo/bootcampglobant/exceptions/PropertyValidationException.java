package com.leo.bootcampglobant.exceptions;

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

  /**
   * Construct a PropertyValidationException with a custom {@code message}, which should indicate which
   * field/property failed the validation, and what should be the proper value.
   *
   * @param message to be showed on the response status error message.
   */
  public PropertyValidationException(String message) {
    super(message);
  }

}
