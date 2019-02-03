package point2.mockconnection;

import point2.ConnectionInfo;

/** Mock Microsoft SQL Server Connection. */
public class SqlServerConnection extends Connection {

  public SqlServerConnection(ConnectionInfo info) {
    super(info);
    System.out.println("SQL Server Connection created! " + info);
  }

  @Override
  public void tryToConnect() {
    System.out.println("Trying to connect to the SQL Server Database...");
  }

}
