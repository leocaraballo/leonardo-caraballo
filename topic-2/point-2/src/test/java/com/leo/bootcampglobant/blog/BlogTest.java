package com.leo.bootcampglobant.blog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class BlogTest {
  private Blog blog;
  private PostRepository mockedPostRepository;
  private Post[] postData = {
      new Post("billy", "The best programming language of 2019", "Assembler.",
          LocalDateTime.of(2019, 2, 1, 12, 0)),
      new Post("james", "How to create a sorting algorithm with O(n)", "The secret is...",
          LocalDateTime.of(2018, 10, 20, 17, 30)),
      new Post("clara", "What's new in 2015?", "With ES5, JS appears to be the next big thing.",
          LocalDateTime.of(2015, 8, 15, 18, 45)),
      new Post("carlos", "How to master TDD", "The first step is...",
          LocalDateTime.of(2019, 2, 1, 11, 40)),
      new Post("kev", "Java 11!", "They did it!! After 6 months, the next version of...",
          LocalDateTime.of(2018, 9, 26, 10, 0)),

  };
  @Before
  public void initialize() {
    mockedPostRepository = mock(PostRepository.class);
    // stub
    when(mockedPostRepository.delete(any())).thenReturn(true);
    blog = new Blog(mockedPostRepository);
  }

  @Test
  public void constructor_repositoryRetrieve() {
    // instantiation in @Before method
    verify(mockedPostRepository).addAll(any());
  }

  @Test
  public void addPost_correctInsertion() {
    blog.addPost(postData[0]);
    assertEquals(Arrays.asList(postData[0]), blog.getPosts());
  }

  @Test
  public void addPost_repositoryInsertion() {
    blog.addPost(postData[0]);
    verify(mockedPostRepository).create(postData[0]);
  }

  @Test
  public void removePost_nonExistingPost() {
    for (Post p : postData) {
      blog.addPost(p);
    }

    Post deletedPost = new Post("dr who", "non-existing", "?", LocalDateTime.now());
    assertFalse(blog.removePost(deletedPost));
    verify(mockedPostRepository, never()).delete(deletedPost);
  }

  @Test
  public void removePost_existingPost() {
    for (Post p : postData) {
      blog.addPost(p);
    }

    Post deletedPost = postData[0];
    assertTrue(blog.removePost(deletedPost));
    verify(mockedPostRepository).delete(deletedPost);
  }

  @Test
  public void getRecentPosts_correctOrder() {
    for (Post p : postData) {
      blog.addPost(p);
    }

    List<Post> expectedPostsList = Arrays.asList(postData);
    // descending timestamp order
    expectedPostsList.sort((postA, postB) -> postB.getTimestamp().compareTo(postA.getTimestamp()));
    assertEquals(expectedPostsList, blog.getRecentPosts());
  }

  @Test
  public void getRecentPosts_correctOrderWithLimit() {
    for (Post p : postData) {
      blog.addPost(p);
    }

    List<Post> expectedPostsList = Arrays.asList(
        // 3 most recent.
        postData[0], postData[3], postData[1]
    );
    assertEquals(expectedPostsList, blog.getRecentPosts(3));
  }
}