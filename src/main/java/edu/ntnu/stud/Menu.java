package edu.ntnu.stud;

import java.util.Scanner;

public class Menu {
  private Scanner scanner;

  public Menu() {
    this.scanner = new Scanner(System.in);
  }

  public void init(TrainDispatchController controller) {
    int choice;

    do {
      System.out.println("Welcome to the Text-Based Menu");
      System.out.println("1. Vis oversikt over togavganger");
      System.out.println("2. Legg til ny togavgang");
      System.out.println("3. Tildele spor til togavgang");
      System.out.println("4. Legg til forsinkelse på togavgang");
      System.out.println("5. Søk etter togavgang (Tognummer)");
      System.out.println("6. Søk etter togavgang (Destinasjon)");
      System.out.println("7. Oppdater klokken");
      System.out.println("8. Avslutt programmet");

      choice = processChoice();

      controller.processChoice(choice);

      System.out.println("Press Enter to continue...");
      scanner.nextLine(); // wait for user to press Enter
    } while (choice != 8);
  }

  public int processChoice() {
    System.out.println("Enter your choice: ");
    int choice = Integer.parseInt(scanner.nextLine());
    return choice;
  }
  public void start(TrainDispatchController controller) {
    init(controller);
    scanner.close();
  }
}
