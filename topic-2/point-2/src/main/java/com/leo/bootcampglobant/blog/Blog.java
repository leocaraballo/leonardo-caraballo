package com.leo.bootcampglobant.blog;

import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Blog class which stores all post entries.
 *
 * When a new instance its created, it tries to retrieve all persisted post using a Repository
 * Implementation.
 * Every time a new post is added, it tries to persist it using a Repository Implementation
 * The default Repository persist the post in a database.
 */
public class Blog {

  private List<Post> posts;
  private PostRepository postRepository;

  public Blog() {
    this(new DbPostRepository());
  }

  public Blog(PostRepository postRepository) {
    this.postRepository = postRepository;
    this.posts = new LinkedList<>();

    this.postRepository.addAll(this.posts);
  }

  public void addPost(Post post) {
    posts.add(post);
    postRepository.create(post);
  }

  /**
   * @return the list of post, ordered by insertion.
   */
  public List<Post> getPosts() {
    return Collections.unmodifiableList(posts);
  }

  /**
   * Deletes a post from the list and the repository, if exists.
   *
   * @param post post to be removed.
   * @return true if the post existed in the list and in the repository, false otherwise.
   */
  public boolean removePost(Post post) {
    return posts.remove(post) && postRepository.delete(post);
  }

  /**
   *
   * @param limit maximum amount of post returned
   * @return the list of <code>limit</code> post, ordered by timestamp (most recent posted)
   */
  public List<Post> getRecentPosts(int limit) {
    return posts.stream().sorted(Comparator.comparing(Post::getTimestamp).reversed())
        .limit(limit).collect(Collectors.toList());
  }

  /**
   * @return the list of (maximum 10) post, ordered by timestamp (most recent posted)
   */
  public List<Post> getRecentPosts() {
    return getRecentPosts(10);
  }
}
