package com.leo.bootcampglobant.daos;

import com.leo.bootcampglobant.models.Student;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

public class StudentDAO extends BasicDAO<Student, Long> {

  public StudentDAO(MongoClient mongoClient, Morphia morphia, String dbName) {
    super(mongoClient, morphia, dbName);
  }

}
