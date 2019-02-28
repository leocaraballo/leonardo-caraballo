package com.leo.bootcampglobant.finalshoppingcart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception threw when a search for a Category with a specific id fails.
 *
 * HTTP Status Code: 404 - Not Found
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends RuntimeException {

  private static final String MESSAGE_PRE = "Couldn't find the category with the id ";
  private static final String MESSAGE_NAME_PRE = "Couldn't find the category with the name of ";

  /**
   * Construct a CategoryNotFoundException with the message 'Couldn't find the category with the id
   * {@code id}', where {@code id} is the value.
   *
   * <p>Example: 'Couldn't find the category with the id {5}'</p>
   *
   * @param id value of the id of the category not found.
   */
  public CategoryNotFoundException(Long id) {
    super(MESSAGE_PRE + id);
  }

  /**
   * Construct a CategoryNotFoundException with the message 'Couldn't find the category with the name of
   * {@code categoryName}'
   *
   * <p>Example: 'Couldn't find the category with the name of {Electronics}'</p>
   *
   * @param categoryName name of the category not found.
   */
  public CategoryNotFoundException(String categoryName) {
    super(MESSAGE_NAME_PRE + categoryName);
  }

}