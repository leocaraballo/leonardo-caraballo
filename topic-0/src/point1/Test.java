package point1;

public class Test {

  public static void try1() {
    DatabaseConnection.INSTANCE.tryToConnect();
  }

  public static void try2() {
    DatabaseConnection.INSTANCE.tryToConnect();
  }

  public static void try3() {
    DatabaseConnection.INSTANCE.tryToConnect();
  }

  public static void main(String[] args) {
    // Should show the message 'Enums are great!!' just once.
    DatabaseConnection.INSTANCE.setInfo(new ConnectionInfo("The Best Server",
        "Things", "user", "pass"));
    try1();
    try2();
    try3();
  }

}
