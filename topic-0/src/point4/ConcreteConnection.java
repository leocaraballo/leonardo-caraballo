package point4;

/**
 * Mock Connection for the Connection Builder.
 */
public class ConcreteConnection implements Connection {

  private String serverName;
  private String databaseName;
  private String username;
  private String password;

  public ConcreteConnection() {
  }

  public String getServerName() {
    return serverName;
  }

  public void setServerName(String serverName) {
    this.serverName = serverName;
  }

  public String getDatabaseName() {
    return databaseName;
  }

  public void setDatabaseName(String databaseName) {
    this.databaseName = databaseName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public void tryToConnect() {
    System.out.println("Trying to connect...");
  }
}
