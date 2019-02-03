package point4;

public class Test {

  public static void main(String[] args) {
    Connection connection = new Director(new ConcreteConnectionBuilder()).constructConnection();

    connection.tryToConnect();
  }

}
