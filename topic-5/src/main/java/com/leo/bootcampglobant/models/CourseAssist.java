package com.leo.bootcampglobant.models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class CourseAssist {

  private ObjectId course_id;
  private Integer year;
  private Integer grade_1;
  private Integer grade_2;
  private Integer grade_3;
  private Integer grade_final;

  public CourseAssist(ObjectId course_id, int year, int grade_1, int grade_2, int grade_3,
      int grade_final) {
    this.course_id = course_id;
    this.year = year;
    this.grade_1 = grade_1;
    this.grade_2 = grade_2;
    this.grade_3 = grade_3;
    this.grade_final = grade_final;
  }

  public CourseAssist() {

  }

  public ObjectId getCourse_id() {
    return course_id;
  }

  public void setCourse_id(ObjectId course_id) {
    this.course_id = course_id;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public Integer getGrade_1() {
    return grade_1;
  }

  public void setGrade_1(Integer grade_1) {
    this.grade_1 = grade_1;
  }

  public Integer getGrade_2() {
    return grade_2;
  }

  public void setGrade_2(Integer grade_2) {
    this.grade_2 = grade_2;
  }

  public Integer getGrade_3() {
    return grade_3;
  }

  public void setGrade_3(Integer grade_3) {
    this.grade_3 = grade_3;
  }

  public Integer getGrade_final() {
    return grade_final;
  }

  public void setGrade_final(Integer grade_final) {
    this.grade_final = grade_final;
  }
}
