package topic0.point2.mockconnection;

import topic0.point2.ConnectionInfo;

/** Mock PostgreSQL Connection. */
public class PgConnection extends Connection {

  public PgConnection(ConnectionInfo info) {
    super(info);
    System.out.println("PostgreSQL Connection created! " + info);
  }

  @Override
  public void tryToConnect() {
    System.out.println("Trying to connect to the PostgreSQL Database...");
  }


}
