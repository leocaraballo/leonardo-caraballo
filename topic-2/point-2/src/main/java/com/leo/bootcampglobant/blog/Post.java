package com.leo.bootcampglobant.blog;

import java.time.LocalDateTime;
import java.util.Objects;

public class Post {
  private String user;
  private String title;
  private String text;
  private LocalDateTime timestamp;

  public Post(String user, String title, String text) {
    this(user, title, text, LocalDateTime.now());
  }

  public Post(String user, String title, String text, LocalDateTime timestamp) {
    if (user == null) {
      throw new IllegalArgumentException("user can't be null");
    }
    if (title == null) {
      throw new IllegalArgumentException("title can't be null");
    }
    if (text == null) {
      throw new IllegalArgumentException("text can't be null");
    }
    if (timestamp == null) {
      throw new IllegalArgumentException("timestamp can't be null");
    }

    this.user = user;
    this.title = title;
    this.text = text;
    this.timestamp = timestamp;
  }

  public String getUser() {
    return user;
  }

  public String getTitle() {
    return title;
  }

  public String getText() {
    return text;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (this == o) {
      return true;
    }
    Post post = (Post) o;
    return user.equals(post.user) &&
        title.equals(post.title) &&
        text.equals(post.text) &&
        timestamp.equals(post.timestamp);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, title, text, timestamp);
  }

  @Override
  public String toString() {
    return "Post{" +
        "user='" + user + '\'' +
        ", title='" + title + '\'' +
        ", text='" + text + '\'' +
        ", timestamp=" + timestamp +
        '}';
  }
}
