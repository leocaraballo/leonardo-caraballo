package com.leo.bootcampglobant.models;

import java.util.List;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

@Entity
public class Course extends BaseEntity {

  private String name;
  private int week_hours;
  @Embedded
  private List<Schedule> scheduleTimes;

  public Course(String name, int week_hours, List<Schedule> scheduleTimes) {
    this.name = name;
    this.week_hours = week_hours;
    this.scheduleTimes = scheduleTimes;
  }

  public Course() {

  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getWeek_hours() {
    return week_hours;
  }

  public void setWeek_hours(int week_hours) {
    this.week_hours = week_hours;
  }

  public List<Schedule> getScheduleTimes() {
    return scheduleTimes;
  }

  public void setScheduleTimes(List<Schedule> scheduleTimes) {
    this.scheduleTimes = scheduleTimes;
  }
}
