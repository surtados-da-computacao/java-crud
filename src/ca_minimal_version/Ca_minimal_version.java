package ca_minimal_version;

import java.sql.SQLException;

public class Ca_minimal_version {

  public static void main(String[] args) {
    Database database;

    try {
      database = new Database();
    } catch (SQLException e) {
      System.out.println("Error to connect to database: " + e.getMessage());
      return;
    }

    Keyboard keyboard = new Keyboard();
    ServiceMenu serviceMenu = new ServiceMenu(database, keyboard);

    String option;

    do {
      System.out.println("--------------------");
      System.out.println("     Main Menu");
      System.out.println("--------------------");
      System.out.println("1 - Manage Services");
      System.out.println("x - Quit");
      System.out.println("....................");

      option = keyboard.readString("Option: ");

      switch (option) {
        case "1" -> serviceMenu.show();
        case "x" -> { return; }
        default -> System.out.println("invalid option");
      }
    } while (true);
  }
}
