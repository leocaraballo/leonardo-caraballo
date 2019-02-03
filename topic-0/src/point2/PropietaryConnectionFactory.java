package point2;

import point2.mockconnection.Connection;
import point2.mockconnection.OracleConnection;
import point2.mockconnection.SqlServerConnection;

/**
 * Concrete Connection Factory for the Database Management Systems which licenses type
 * are proprietary.
 */
public class PropietaryConnectionFactory extends AbstractConnectionFactory {

  @Override
  public Connection createConnection(String dbmsName, ConnectionInfo info)
      throws DbmsNotFoundException {

    if (dbmsName == null) {
      return null;
    }

    if (dbmsName.equalsIgnoreCase("sqlserver")) {
      return new SqlServerConnection(info);
    } else if (dbmsName.equalsIgnoreCase("oracle")) {
      return new OracleConnection(info);
    }

    throw new DbmsNotFoundException("Can't find '" + dbmsName + "' connection class.");
  }
}
