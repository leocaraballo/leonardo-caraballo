package topic0.point2.mockconnection;

import topic0.point2.ConnectionInfo;

/** Base Mock Class for a Connection */
public abstract class Connection {
  private ConnectionInfo info;

  protected Connection(ConnectionInfo info) {
    this.info = info;
  }

  public ConnectionInfo getInfo() {
    return info;
  }

  public abstract void tryToConnect();

}
