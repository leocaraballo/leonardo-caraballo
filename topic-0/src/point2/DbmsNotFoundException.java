package point2;

/**
 * Exception threw by Connection Factories when a Database Management System string name doesn't
 * match.
 */
public class DbmsNotFoundException extends Exception {
  public DbmsNotFoundException(String message) {
    super(message);
  }
}
