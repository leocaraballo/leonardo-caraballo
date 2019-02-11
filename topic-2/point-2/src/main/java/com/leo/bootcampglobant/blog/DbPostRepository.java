package com.leo.bootcampglobant.blog;

import java.util.List;

/**
 * "Real" Repository implementation.
 *
 * Since the test should use mocks, this class should not be actually used.
 */
public class DbPostRepository implements PostRepository {

  @Override
  public List<Post> retrieveAll() {
    System.out.println("Use mocks");
    // Read all the Post in the Database and return them.
    return null;
  }

  @Override
  public void addAll(List<Post> posts) {
    // Fill the posts list with all the Posts in the database.
    System.out.println("Use mocks");
  }

  @Override
  public boolean create(Post post) {
    System.out.println("Use mocks");
    // Insert a new post in the Database.
    return true;
  }

  @Override
  public boolean delete(Post post) {
    System.out.println("Use mocks");
    // Delete the post from the database.
    return true;
  }
}
