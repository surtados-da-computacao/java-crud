package ca_minimal_version;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

  private Connection connection;

  public Database() throws SQLException {
    String url = "jdbc:mysql://localhost:3306/";
    String database = "crud_rental";
    String user = "root";
    String password = "rootpwd";
    this.connection = DriverManager.getConnection(url + database, user, password);
  }

  public Connection getConnection() {
    return connection;
  }
}
