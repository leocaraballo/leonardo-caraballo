package point2;

import point2.mockconnection.Connection;

public abstract class AbstractConnectionFactory {

  /**
   * Creates a Concrete Database Connection based on <code>dbmsName</code>.
   *
   * @param dbmsName Database Management System Name of the Connection
   * @param info Connection Information
   * @return a new Connection object of the specified type
   * @throws DbmsNotFoundException if <code>dbmsName</code> doesn't match with any connection.
   */
  public abstract Connection createConnection(String dbmsName, ConnectionInfo info)
      throws DbmsNotFoundException;

}
