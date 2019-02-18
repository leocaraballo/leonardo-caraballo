package com.leo.bootcampglobant;

import com.mysql.cj.xdevapi.SqlDataResult;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

public class HighSchoolQuery {

  private static final String PROCEDURE_CALL = "{CALL teacher_schedule(?)}";

  private static final String TEACHER_NAME_QUERY = "SELECT last_name, first_name "
                                                 + "FROM Teacher "
                                                 + "WHERE id = ?";

  private static final String TEACHER_TIMELINE = "SELECT CASE S.day "
                                                 + "WHEN 0 THEN 'Monday' "
                                                 + "WHEN 1 THEN 'Tuesday' "
                                                 + "WHEN 2 THEN 'Wednesday' "
                                                 + "WHEN 3 THEN 'Thursday' "
                                                 + "WHEN 4 THEN 'Friday' "
                                                 + "WHEN 5 THEN 'Saturday' "
                                                 + "WHEN 6 THEN 'Sunday' "
                                               + "END AS day, S.time_start, S.time_end, C.name "
                                               + "FROM Dictated D JOIN Course C "
                                                   + "ON D.course_name = C.name "
                                                 + "JOIN Schedule S "
                                                   + "ON D.schedule_id = S.id "
                                               + "WHERE C.teacher_id = ? "
                                               + "ORDER BY S.day ASC, S.time_start ASC ";

  /**
   * Prints the teacher name and they schedule timeline.
   *
   * If <code>useStoredProcedure</code> is true, it is assumed that there's a stored procedure
   * in the database, named teacher_schedule, accepts as an argument the teacher's id, and make
   * the exact select statements that are called here.
   *
   * @param teacher_id teacher's id
   * @param useStoredProcedure true if want to use the stored procedure in the database
   */
  public void teacherScheduleTimeline(final int teacher_id, final boolean useStoredProcedure) {
    try (Connection connection = MysqlAccessDb.INSTANCE.getConnection()) {
      if (useStoredProcedure) {
        CallableStatement cs = connection.prepareCall(PROCEDURE_CALL);
        cs.setInt(1, teacher_id);

        if (cs.execute()) {
          ResultSet rs = cs.getResultSet();
          if (printTeacher(rs)) {
            cs.getMoreResults();
            rs = cs.getResultSet();
            printTimeline(rs);
          }
        } else {
          System.out.println("Procedure doesn't exist.");
        }

      } else {
        PreparedStatement ps = connection.prepareStatement(TEACHER_NAME_QUERY);
        ps.setInt(1, teacher_id);
        ResultSet rs = ps.executeQuery();

        if (printTeacher(rs)) {
          ps = connection.prepareStatement(TEACHER_TIMELINE);
          ps.setInt(1, teacher_id);
          rs = ps.executeQuery();

          printTimeline(rs);
        }
      }
    } catch (SQLException | NamingException e) {
      e.printStackTrace();
    }
  }

  private boolean printTeacher(ResultSet rs) throws SQLException{
    if (!rs.next()) {
      return false;
    }
    System.out.format("Teacher: %s, %s\n", rs.getString("last_name"),
                                           rs.getString("first_name"));
    return true;
  }

  private void printTimeline(ResultSet rs) throws SQLException {
    while (rs.next()) {
      System.out.println("\t" + rs.getString("day") + " " + rs.getTime("time_start")
          + " - " + rs.getTime("time_end") + ": " + rs.getString("name"));
    }
  }
}
