package point2;

import point2.mockconnection.Connection;

public class Test {

  public static void main(String[] args) {
    ConnectionInfo info = new ConnectionInfo("The Best Server", "Things",
        "user", "pass");
    AbstractConnectionFactory connectionFactory =
        ConnectionFactoryProducer.getConnectionFactory(false);
    Connection connection = null;

    try {
      connection = connectionFactory.createConnection("oracle", info);
    } catch (DbmsNotFoundException ex) {
      System.out.println(ex.getMessage());
    }
  }
}
