package ca_minimal_version;

import java.util.Scanner;

public class Keyboard {

  private Scanner scanner = new Scanner(System.in);

  public String readString() {
    String value = scanner.nextLine();
    return value.trim();
  }

  public int readInt() {
    while (true) {
      String value = scanner.nextLine();
      try {
        return Integer.parseInt(value);
      } catch (NumberFormatException e) {
        System.out.println("invalid int");
      }
    }
  }

  public double readDouble() {
    while (true) {
      String value = scanner.nextLine();
      try {
        return Double.parseDouble(value);
      } catch (NumberFormatException e) {
        System.out.println("invalid double");
      }
    }
  }
}
