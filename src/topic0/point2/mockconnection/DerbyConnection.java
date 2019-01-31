package topic0.point2.mockconnection;

import topic0.point2.ConnectionInfo;

/** Mock Apache Derby Connection. */
public class DerbyConnection extends Connection {

  public DerbyConnection(ConnectionInfo info) {
    super(info);
    System.out.println("Apache Derby Connection created! " + info);
  }

  @Override
  public void tryToConnect() {
    System.out.println("Trying to connect to the Derby Database...");
  }

}
