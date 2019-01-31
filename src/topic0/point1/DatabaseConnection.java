package topic0.point1;

/** Singleton class, implemented using enums, for a Database Connection. */
enum DatabaseConnection {
  INSTANCE;

  private ConnectionInfo info;

  private DatabaseConnection() {
    System.out.println("Enums are great!!");
  }

  public void setInfo(ConnectionInfo info) {
    this.info = info;
  }

  public ConnectionInfo getInfo() {
    return this.info;
  }

  public void tryToConnect() {
    System.out.println("INFO: " + info + ". Trying to Connect...");
  }
}
