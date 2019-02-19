package com.leo.bootcampglobant.models;

import java.time.LocalDateTime;
import java.util.List;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

@Entity
public class Teacher extends BaseEntity {

  private Long teacher_id;
  private String firstName;
  private String lastName;
  private LocalDateTime birthDate;
  @Embedded
  private List<CourseName> courseNames;

  public Long getTeacher_id() {
    return teacher_id;
  }

  public void setTeacher_id(Long teacher_id) {
    this.teacher_id = teacher_id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public LocalDateTime getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDateTime birthDate) {
    this.birthDate = birthDate;
  }

  public List<CourseName> getCourseNames() {
    return courseNames;
  }

  public void setCourseNames(List<CourseName> courseNames) {
    this.courseNames = courseNames;
  }
}
