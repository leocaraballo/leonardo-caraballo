package topic0.point2.mockconnection;

import topic0.point2.ConnectionInfo;

/** Mock MySQL Connection. */
public class MysqlConnection extends Connection{

  public MysqlConnection(ConnectionInfo info) {
    super(info);
    System.out.println("MySQL Connection created! " + info);
  }

  @Override
  public void tryToConnect() {
    System.out.println("Trying to connect to the MySQL Database...");
  }

}
