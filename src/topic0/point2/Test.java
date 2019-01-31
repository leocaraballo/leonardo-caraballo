package topic0.point2;

import java.sql.Connection;

public class Test {

  public static void main(String[] args) {
    ConnectionInfo info = new ConnectionInfo("The Best Server", "Things",
        "user", "pass");
    AbstractConnectionFactory connectionFactory =
        ConnectionFactoryProducer.getConnectionFactory(true);
    Connection connection = null;

    try {
      connection = connectionFactory.createConnection("mysql", info);
    } catch (DbmsNotFoundException ex) {
      System.out.println(ex.getMessage());
    }
  }
}
