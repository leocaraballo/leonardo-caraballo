package point4;

/**
 * Class that manage the Connection building process, through the use of a Connection Builder.
 */
public class Director {
  private ConnectionBuilder builder;

  public Director(ConnectionBuilder builder) {
    this.builder = builder;
  }

  public Connection constructConnection() {
    return builder.setServerName("The Best Server")
                  .setDatabaseName("Things")
                  .setUsername("user")
                  .setPassword("pass")
                  .build();
  }
}
