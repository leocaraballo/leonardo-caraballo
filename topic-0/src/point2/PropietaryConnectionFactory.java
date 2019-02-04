package point2;

import point2.mockconnection.Connection;
import point2.mockconnection.OracleConnection;
import point2.mockconnection.SqlServerConnection;

/**
 * Concrete Connection Factory for the Database Management Systems which licenses type
 * are proprietary.
 */
public class PropietaryConnectionFactory extends AbstractConnectionFactory {

  public static final String SQL_SERVER = "sqlserver";
  public static final String ORACLE = "oracle";

  @Override
  public Connection createConnection(String dbmsName, ConnectionInfo info)
      throws DbmsNotFoundException {

    if (dbmsName == null) {
      return null;
    }

    Connection connection = null;

    switch (dbmsName.toLowerCase()) {
      case SQL_SERVER:
        connection = new SqlServerConnection(info);
        break;
      case ORACLE:
        connection = new OracleConnection(info);
        break;
      default:
        throw new DbmsNotFoundException("Can't find '" + dbmsName + "' connection class.");
    }

    return connection;
  }
}
