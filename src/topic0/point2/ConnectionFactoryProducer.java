package topic0.point2;

public class ConnectionFactoryProducer {

  public static AbstractConnectionFactory getConnectionFactory(boolean openSource) {
    return openSource ? new OpenSourceConnectionFactory() : new PropietaryConnectionFactory();
  }

}
