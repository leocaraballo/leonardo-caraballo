package point2;

/** Information necessary for the correct instantiation of the connection. */
public class ConnectionInfo {
  private String serverName;
  private String dbName;
  private String username;
  private String password;

  public ConnectionInfo(String serverName, String dbName, String username, String password) {
    this.serverName = serverName;
    this.dbName = dbName;
    this.username = username;
    this.password = password;
  }

  public String getServerName() {
    return serverName;
  }

  public String getDbName() {
    return dbName;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public String toString() {
    return "Server: " + serverName + ", Database: " + dbName + ", Username: " + username +
        ", Password: " + password;
  }
}
