package topic0.point1;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Singleton class, implemented using enums, for a Database Connection.
 *
 * <p>It uses the class DataSource to get the Connection because, according to Oracle, is the
 * preferred approach. It assumes that an InitialContext has been previously set with all
 * the necessary Database Information, and the key used for the lookup should be stored in
 * the local variable <code>contextName</code>.</p>
 */
enum Database {
  INSTANCE;

  private Connection connection;

  private Database() {
    String contextName = "jdbc/control";

    try {
      DataSource ds = (DataSource)new InitialContext().lookup(contextName);
      connection = ds.getConnection();
    } catch (NamingException ex) {
      System.err.println("Context name '" + contextName +"' doesn't exist.");
      ex.printStackTrace();
    } catch (SQLException ex) {
      System.err.println("Couldn't access to the specified database.");
      ex.printStackTrace();
    }
  }

  public Connection getConnection() {
    return connection;
  }

}
