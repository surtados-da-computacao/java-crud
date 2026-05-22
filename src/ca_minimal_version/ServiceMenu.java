package ca_minimal_version;

import java.sql.*;

public class ServiceMenu {

  private Connection db;
  private Keyboard keyboard;

  public ServiceMenu(Database database, Keyboard keyboard) {
    this.db = database.getConnection();
    this.keyboard = keyboard;
  }

  public void show() {
    String option;

    do {
      System.out.println("--------------------");
      System.out.println("   Service Menu");
      System.out.println("--------------------");
      System.out.println("1 - List all services");
      System.out.println("2 - Show one service by ID");
      System.out.println("3 - Create service");
      System.out.println("4 - Update service by ID");
      System.out.println("5 - Delete service by ID");
      System.out.println("x - Back to main menu");
      System.out.println("....................");
      System.out.print("Option: ");

      option = keyboard.readString();

      switch (option) {
        case "1" -> listAllServices();
        case "2" -> showOneServiceById();
        case "3" -> createNewService();
        case "4" -> updateServiceNameById();
        case "5" -> deleteServiceById();
        case "x" -> { return; }
        default -> System.out.println("invalid option");
      }
    } while (true);
  }

  private void listAllServices() {
    try {
      String sql = "SELECT * FROM service_details;";
      PreparedStatement stmt = db.prepareStatement(sql);

      ResultSet result = stmt.executeQuery();

      while (result.next()) {
        System.out.println(result.getInt("service_id"));
        System.out.println(result.getString("service_name"));
        System.out.println(result.getDouble("service_charge"));
        System.out.println();
      }
    } catch (SQLException e) {
      System.out.println("listAllServices: " + e.getMessage());
    }
  }

  private void showOneServiceById() {
    System.out.print("Service ID: ");
    int serviceID = keyboard.readInt();

    String sql = "SELECT * FROM service_details WHERE service_id = ?;";

    try {
      PreparedStatement stmt = db.prepareStatement(sql);
      stmt.setInt(1, serviceID);

      ResultSet result = stmt.executeQuery();

      if (result.next()) {
        System.out.println();
        System.out.println(result.getInt("service_id"));
        System.out.println(result.getString("service_name"));
        System.out.println(result.getDouble("service_charge"));
        System.out.println();
      } else {
        System.out.println();
        System.out.println("service not found");
        System.out.println();
      }

    } catch (SQLException e) {
      System.out.println("showOneServiceById error: " + e.getMessage());
    }
  }

  private void createNewService() {
    System.out.print("Service name: ");
    String serviceName = keyboard.readString();
    System.out.print("Service charge: ");
    double serviceCharge = keyboard.readDouble();

    String sql = "INSERT INTO service_details (service_name, service_charge) VALUES (?, ?);";

    try {
      PreparedStatement stmt = db.prepareStatement(sql);
      stmt.setString(1, serviceName);
      stmt.setDouble(2, serviceCharge);

      int rows = stmt.executeUpdate();
      System.out.println(rows + " rows inserted");
    } catch (SQLException e) {
      System.out.println("createNewService error: " + e.getMessage());
    }
  }

  private void updateServiceNameById() {
    System.out.print("Service ID: ");
    int serviceId = keyboard.readInt();
    System.out.print("Service name: ");
    String serviceName = keyboard.readString();

    String sql = "UPDATE service_details SET service_name = ? WHERE service_id = ?";

    try {
      PreparedStatement stmt = db.prepareStatement(sql);
      stmt.setString(1, serviceName);
      stmt.setInt(2, serviceId);

      int rows = stmt.executeUpdate();
      System.out.println(rows + " rows updated");
    } catch (SQLException e) {
      System.out.println("updateServiceNameById error: " + e.getMessage());
    }
  }

  private void deleteServiceById() {
    System.out.print("Service ID: ");
    int serviceId = keyboard.readInt();

    String sql = "DELETE FROM service_details WHERE service_id = ?";

    try {
      PreparedStatement stmt = db.prepareStatement(sql);
      stmt.setInt(1, serviceId);

      int rows = stmt.executeUpdate();
      System.out.println(rows + " rows deleted");
    } catch (SQLException e) {
      System.out.println("deleteServiceById error: " + e.getMessage());
    }
  }
}
