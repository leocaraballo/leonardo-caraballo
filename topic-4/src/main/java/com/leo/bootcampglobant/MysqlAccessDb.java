package com.leo.bootcampglobant;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import javax.naming.spi.InitialContextFactoryBuilder;
import javax.naming.spi.NamingManager;
import javax.sql.DataSource;

public enum MysqlAccessDb {
  INSTANCE;

  private final String DB_NAME = "high_school";
  private final String USER = "root";
  private final String PASS = "";

  MysqlAccessDb() {
    setupCustomInitialContext();
  }

  private void setupCustomInitialContext() {
    try {
      NamingManager.setInitialContextFactoryBuilder(initialContextFactoryEnvironment ->
          environment -> new InitialContext() {
            private Hashtable<String, DataSource> dataSources = new Hashtable<>();
            @Override
            public Object lookup(String name) throws NamingException {
              if (dataSources.isEmpty()) {
                MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
                ds.setURL("jdbc:mysql://localhost:3306/" + DB_NAME);
                ds.setUser(USER);
                ds.setPassword(PASS);
                dataSources.put("jdbc/" + DB_NAME, ds);
              }
              if (dataSources.containsKey(name)) {
                return dataSources.get(name);
              }
              throw new NamingException("Unable to find datasource: " + name);
            }
          });
    }
    catch (NamingException ne) {
      ne.printStackTrace();
    }
  }

  public Connection getConnection() throws NamingException, SQLException {
    return ((DataSource) new InitialContext().lookup("jdbc/" + DB_NAME)).getConnection();
  }

}
