package ca_minimal_version;

import java.sql.*;
import java.util.Scanner;

public class Ca_minimal_version {

  static Scanner myKB = new Scanner(System.in);
  static Connection db;

  public static void main(String[] args) {
    try {
      String url = "jdbc:mysql://localhost:3306/";
      String database = "crud_rental";
      String user = "root";
      String password = "rootpwd";
      db = DriverManager.getConnection(url + database, user, password);
    } catch (SQLException e) {
      System.out.println("Error to connect to database: " + e.getMessage());
      return;
    }

    String option;

    do {
      System.out.println("--------------------");
      System.out.println("     Main Menu");
      System.out.println("--------------------");
      System.out.println("1 - Manage Services");
      System.out.println("x - Quit");
      System.out.println("....................");
      System.out.print("Option: ");

      option = readString();

      switch (option) {
        case "1" -> serviceMenu();
        case "x" -> { return; }
        default ->  System.out.println("invalid option");
      }
    } while (true);
  }

  public static void serviceMenu() {
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

      option = readString();

      switch (option) {

        case "1" -> { listAllServices();}
        case "2" -> { showOneServiceById();}
        case "3" -> { createNewService(); }
        case "4" -> { updateServiceNameById(); }
        case "5" -> { deleteServiceById();}
        case "x" -> { return; }

        default -> {
          System.out.println("invalid option");
        }
      }
    } while (true);
  }

  public static void listAllServices() {
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

  public static void showOneServiceById() {
    System.out.print("Service ID: ");
    int serviceID = readInt();

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

  public static void createNewService() {
    System.out.print("Service name: ");
    String serviceName = readString();
    System.out.print("Service charge: ");
    double serviceCharge = readDouble();

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

  public static void updateServiceNameById() {
    System.out.print("Service ID: ");
    int serviceId =  readInt();
    System.out.print("Service name: ");
    String serviceName = readString();

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

  public static void deleteServiceById() {
    System.out.print("Service ID: ");
    int serviceId =  readInt();

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

  public static String readString() {
    String value = myKB.nextLine();
    return value.trim();
  }

  public static int readInt() {
    while(true) {
      String value = myKB.nextLine();
      try {
        return Integer.parseInt(value);
      } catch (NumberFormatException e) {
        System.out.println("invalid int");
      }
    }
  }

  public static double readDouble() {
    while(true) {
      String value = myKB.nextLine();
      try {
        return Double.parseDouble(value);
      } catch (NumberFormatException e) {
        System.out.println("invalid double");
      }
    }
  }
}
