package com.leo.bootcampglobant.finalshoppingcart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception threw when a user tries to request an unauthorized resource
 *
 * HTTP Status Code: 401 - Unauthorized
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class PermissionDeniedException extends RuntimeException {

  private static final String MESSAGE = "User unauthorized to perform that action (token mismatch)";

  /**
   * Construct a PermissionDeniedException with the message 'User unauthorized to perform that
   * action (token mismatch)'
   */
  public PermissionDeniedException() {
    super(MESSAGE);
  }
}