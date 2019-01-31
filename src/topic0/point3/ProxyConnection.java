package topic0.point3;

/**
 * Proxy Pattern of the ConcreteConnection.
 *
 * Besides restricting access to the Connection parts to the Cliente (after instantiation), it
 * adds a new functionality (showInformation).
 */
public class ProxyConnection implements Connection {

  private String serverName;
  private String databaseName;
  private String username;
  private String password;

  private ConcreteConnection connection;

  public ProxyConnection(String serverName, String databaseName, String username,
      String password) {

    this.serverName = serverName;
    this.databaseName = databaseName;
    this.username = username;
    this.password = password;
  }

  @Override
  public void tryToConnect() {
    if (connection == null) {
      connection = new ConcreteConnection(serverName, databaseName, username, password);
    }

    connection.tryToConnect();
  }

  public void showInformation() {
    System.out.println("Server Name: " + serverName);
    System.out.println("Database Name: " + databaseName);
    System.out.println("Username: " + username);
    System.out.println("Password: " + password);
  }
}
