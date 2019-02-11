package com.leo.bootcampglobant.blog;

import java.util.List;

/**
 * Interface using the Repository Design Pattern.
 * Per exercise requirements, it only need the operations to retrieve all elements, create a new
 * one (add), and element deletion (remove).
 */
public interface PostRepository {
  List<Post> retrieveAll();

  /**
   * Fills the list with all the post stored in the Repository.
   * @param posts list to be filled.
   */
  void addAll(List<Post> posts);
  boolean create(Post post);
  boolean delete(Post post);
}
