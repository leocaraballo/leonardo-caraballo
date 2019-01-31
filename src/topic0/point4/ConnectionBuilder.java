package topic0.point4;

import java.sql.Connection;

public interface ConnectionBuilder {
  Connection build();

  ConnectionBuilder setServerName(String serverName);
  ConnectionBuilder setDatabaseName(String databaseName);
  ConnectionBuilder setUsername(String username);
  ConnectionBuilder setPassword(String password);
}
