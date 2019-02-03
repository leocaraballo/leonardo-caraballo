package point2.mockconnection;

import point2.ConnectionInfo;

/** Mock Oracle Database Connection. */
public class OracleConnection extends Connection {

  public OracleConnection(ConnectionInfo info) {
    super(info);
    System.out.println("Oracle Database Connection created! " + info);
  }

  @Override
  public void tryToConnect() {
    System.out.println("Trying to connect to the Oracle Database Database...");
  }

}
