package topic0.point2;

import java.sql.Connection;
import topic0.point2.mockconnection.DerbyConnection;
import topic0.point2.mockconnection.MysqlConnection;
import topic0.point2.mockconnection.PgConnection;

/**
 * Concrete Connection Factory for the Database Management Systems which licenses are
 * an open-source variant.
 */
public class OpenSourceConnectionFactory extends AbstractConnectionFactory {

  @Override
  public Connection createConnection(String dbmsName, ConnectionInfo info)
      throws DbmsNotFoundException {

    if (dbmsName == null) {
      return null;
    }

    if (dbmsName.equalsIgnoreCase("mysql")) {
      return new MysqlConnection(info);
    } else if (dbmsName.equalsIgnoreCase("postgres")) {
      return new PgConnection(info);
    } else if (dbmsName.equalsIgnoreCase("derby")) {
      return new DerbyConnection(info);
    }

    throw new DbmsNotFoundException("Can't find '" + dbmsName + "' connection class.");
  }

}
