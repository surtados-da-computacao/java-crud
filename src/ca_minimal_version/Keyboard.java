package ca_minimal_version;

import java.util.Scanner;

public class Keyboard {

  private Scanner scanner = new Scanner(System.in);

  public String readString(String label) {
    System.out.print(label);
    String value = scanner.nextLine();
    return value.trim();
  }

  public int readInt(String label) {
    while (true) {
      System.out.print(label);
      String value = scanner.nextLine();
      try {
        return Integer.parseInt(value);
      } catch (NumberFormatException e) {
        System.out.println("invalid int");
      }
    }
  }

  public double readDouble(String label) {
    while (true) {
      System.out.print(label);
      String value = scanner.nextLine();
      try {
        return Double.parseDouble(value);
      } catch (NumberFormatException e) {
        System.out.println("invalid double");
      }
    }
  }
}
