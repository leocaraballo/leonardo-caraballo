package com.leo.bootcampglobant.daos;

import com.leo.bootcampglobant.models.Teacher;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

public class TeacherDAO extends BasicDAO<Teacher, Long> {

  public TeacherDAO(MongoClient mongoClient, Morphia morphia, String dbName) {
    super(mongoClient, morphia, dbName);
  }

}
