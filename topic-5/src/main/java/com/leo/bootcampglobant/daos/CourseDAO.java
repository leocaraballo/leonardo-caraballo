package com.leo.bootcampglobant.daos;

import com.leo.bootcampglobant.models.Course;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

public class CourseDAO extends BasicDAO<Course, String> {

  public CourseDAO(MongoClient mongoClient, Morphia morphia, String dbName) {
    super(mongoClient, morphia, dbName);
  }
}
