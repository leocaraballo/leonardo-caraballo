package com.leo.bootcampglobant.models;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class CourseName {

  private String courseName;

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

}
