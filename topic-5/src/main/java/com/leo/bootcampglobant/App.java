package com.leo.bootcampglobant;

import com.leo.bootcampglobant.daos.CourseDAO;
import com.leo.bootcampglobant.daos.StudentDAO;
import com.leo.bootcampglobant.models.Course;
import com.leo.bootcampglobant.models.CourseAssist;
import com.leo.bootcampglobant.models.Schedule;
import com.leo.bootcampglobant.models.Student;
import com.mongodb.MongoClient;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.QueryResults;

public class App {

  private static final String DB_NAME = "high-school";

  private static final MongoClient MONGO_CLIENT = new MongoClient();
  private static final Morphia MORPHIA = new Morphia();
  private static final CourseDAO courseDAO = new CourseDAO(MONGO_CLIENT, MORPHIA, DB_NAME);
  private static final StudentDAO studentDAO = new StudentDAO(MONGO_CLIENT, MORPHIA, DB_NAME);

  public static void main(String[] args) {
    printStudents();
  }

  public static ObjectId insertData() {
    Course course = new Course("Introduction to Computer Science", 6,
                                Arrays.asList(
                                    new Schedule("Monday", "08:00", "10:00"),
                                    new Schedule("Wednesday", "10:00", "12:00"),
                                    new Schedule("Friday", "14:00", "16:00")
                                ));

    courseDAO.save(course);

    Student student1 = new Student(1L, "Hetti", "Harmon",
        LocalDateTime.of(1999, 12, 4, 7, 52, 25),
        Collections.singletonList(new CourseAssist(course.getId(), 2018, 7, 6, 8, 9))
    );
    Student student2 = new Student(3L, "Georgy", "Mieville",
        LocalDateTime.of(1999, 12, 4, 7, 52, 25),
        Collections.singletonList(new CourseAssist(course.getId(), 2018, 7, 6, 4, 2))
    );
    Student student3 = new Student(5L, "Dina", "Gannicott",
        LocalDateTime.of(1999, 12, 4, 7, 52, 25),
        Collections.singletonList(new CourseAssist(course.getId(), 2018, 5, 4, 2, 10))
    );

    studentDAO.save(student1);
    studentDAO.save(student2);
    studentDAO.save(student3);

    return course.getId();
  }

  public static void printStudents() {
    ObjectId courseId = insertData();
    Datastore datastore = MORPHIA.createDatastore(MONGO_CLIENT, DB_NAME);
    Query<Student> query = datastore.createQuery(Student.class);
    Query<CourseAssist> criteria = datastore.createQuery(CourseAssist.class)
        .field("course_id").equal(courseId).field("grade_final").greaterThan(1);

    query.field("assist").elemMatch(criteria);

    QueryResults<Student> queryResults = studentDAO.find(query);

    for (Student student : queryResults) {
      System.out.format("%s, %s - %d\n", student.getLastName(), student.getFirstName(),
          student.getAssist().get(0).getGrade_final());
    }
  }
}
