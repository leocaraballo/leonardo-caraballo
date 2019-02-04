package point2;

import point2.mockconnection.Connection;
import point2.mockconnection.DerbyConnection;
import point2.mockconnection.MysqlConnection;
import point2.mockconnection.PgConnection;

/**
 * Concrete Connection Factory for the Database Management Systems which licenses are
 * an open-source variant.
 */
public class OpenSourceConnectionFactory extends AbstractConnectionFactory {

  private static final String MYSQL = "mysql";
  private static final String POSTGRES = "postgres";
  private static final String DERBY = "derby";

  @Override
  public Connection createConnection(String dbmsName, ConnectionInfo info)
      throws DbmsNotFoundException {

    if (dbmsName == null) {
      return null;
    }

    if (MYSQL.equalsIgnoreCase(dbmsName)) {
      return new MysqlConnection(info);
    } else if (POSTGRES.equalsIgnoreCase(dbmsName)) {
      return new PgConnection(info);
    } else if (DERBY.equalsIgnoreCase(dbmsName)) {
      return new DerbyConnection(info);
    }

    throw new DbmsNotFoundException("Can't find '" + dbmsName + "' connection class.");
  }

}
