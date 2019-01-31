package topic0.point4;

import java.sql.Connection;

/**
 * Class that manage the Connection building process, through the use of a Connection Builder.
 */
public class Director {
  private ConnectionBuilder builder;

  public Director(ConnectionBuilder builder) {
    this.builder = builder;
  }

  public Connection constructConneciton() {
    return builder.setServerName("The Best Server")
                  .setDatabaseName("Things")
                  .setUsername("user")
                  .setPassword("pass")
                  .build();
  }
}
