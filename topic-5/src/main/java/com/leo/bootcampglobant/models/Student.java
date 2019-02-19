package com.leo.bootcampglobant.models;

import java.time.LocalDateTime;
import java.util.List;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

@Entity
public class Student extends BaseEntity {

  private Long registrationNumber;
  private String firstName;
  private String lastName;
  private LocalDateTime birthDate;
  @Embedded
  private List<CourseAssist> assist;

  public Student(Long registrationNumber, String firstName, String lastName,
      LocalDateTime birthDate, List<CourseAssist> assist) {
    this.registrationNumber = registrationNumber;
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthDate = birthDate;
    this.assist = assist;
  }

  public Student() {

  }

  public Long getRegistrationNumber() {
    return registrationNumber;
  }

  public void setRegistrationNumber(Long registrationNumber) {
    this.registrationNumber = registrationNumber;
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

  public List<CourseAssist> getAssist() {
    return assist;
  }

  public void setAssist(List<CourseAssist> assist) {
    this.assist = assist;
  }

}
