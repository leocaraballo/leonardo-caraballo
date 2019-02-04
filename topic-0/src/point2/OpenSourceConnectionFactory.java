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

    Connection connection = null;

    switch (dbmsName.toLowerCase()) {
      case MYSQL:
        connection = new MysqlConnection(info);
        break;
      case POSTGRES:
        connection = new PgConnection(info);
        break;
      case DERBY:
        connection = new DerbyConnection(info);
        break;
      default:
        throw new DbmsNotFoundException("Can't find '" + dbmsName + "' connection class.");
    }
    return connection;
  }

}
