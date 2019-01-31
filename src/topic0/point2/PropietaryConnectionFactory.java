package topic0.point2;

import topic0.point2.mockconnection.Connection;
import topic0.point2.mockconnection.OracleConnection;
import topic0.point2.mockconnection.SqlServerConnection;

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
