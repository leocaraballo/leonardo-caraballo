package point3;

public class Test {

  public static void main(String[] args) {
    ProxyConnection pc = new ProxyConnection("The Best Server", "Things",
        "user", "pass");

    pc.tryToConnect();
    pc.showInformation();
  }

}
