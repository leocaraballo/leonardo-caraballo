package point4;

public class ConcreteConnectionBuilder implements ConnectionBuilder {

  private ConcreteConnection tempConnection;

  public ConcreteConnectionBuilder() {
    this.tempConnection = new ConcreteConnection();
  }

  @Override
  public Connection build() {
    ConcreteConnection connection = new ConcreteConnection();

    connection.setServerName(tempConnection.getServerName());
    connection.setDatabaseName(tempConnection.getDatabaseName());
    connection.setUsername(tempConnection.getUsername());
    connection.setPassword(tempConnection.getPassword());

    return connection;
  }

  @Override
  public ConnectionBuilder setServerName(String serverName) {
    this.tempConnection.setServerName(serverName);
    return this;
  }

  @Override
  public ConnectionBuilder setDatabaseName(String databaseName) {
    this.tempConnection.setDatabaseName(databaseName);
    return this;
  }

  @Override
  public ConnectionBuilder setUsername(String username) {
    this.tempConnection.setUsername(username);
    return this;
  }

  @Override
  public ConnectionBuilder setPassword(String password) {
    this.tempConnection.setPassword(password);
    return this;
  }
}
