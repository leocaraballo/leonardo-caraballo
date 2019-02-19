package com.leo.bootcampglobant.models;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class Schedule {

  private String day;
  private String timeStart;
  private String timeEnd;

  public Schedule(String day, String timeStart, String timeEnd) {
    this.day = day;
    this.timeStart = timeStart;
    this.timeEnd = timeEnd;
  }

  public Schedule() {

  }

  public String getDay() {
    return day;
  }

  public void setDay(String day) {
    this.day = day;
  }

  public String getTimeStart() {
    return timeStart;
  }

  public void setTimeStart(String timeStart) {
    this.timeStart = timeStart;
  }

  public String getTimeEnd() {
    return timeEnd;
  }

  public void setTimeEnd(String timeEnd) {
    this.timeEnd = timeEnd;
  }
}
