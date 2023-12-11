package edu.ntnu.stud;

import java.sql.SQLOutput;
import java.util.Scanner;
/**
 * This class represents a menu for a train dispatch system.
 * It handles user input and interacts with a TrainDispatchController
 * to perform actions based on the user's choices.
 */
public class Menu {

  // Scanner object to read user input
  private Scanner scanner;

  /**
   * Constructor for the Menu class.
   * Initializes the scanner object.
   */
  public Menu() {
    this.scanner = new Scanner(System.in);
  }

  /**
   * This method handles the main menu interaction.
   * It displays the menu, gets the user's choice, and passes that choice to the TrainDispatchController.
   * This continues in a loop until the user chooses to exit.
   *
   * @param controller The TrainDispatchController that will handle the user's choice.
   */
  public void init(TrainDispatchController controller) {

    int choice;

    do {
      clearScreen();
      System.out.println(controller.getTrainDispatchSystem().formatTrainsInTableFormat(controller.getTrainDispatchSystem().listAllTrains()));
      System.out.println("Welcome to the Text-Based Menu");
      System.out.println("1. Legg til ny togavgang");
      System.out.println("2. Tildele spor til togavgang");
      System.out.println("3. Legg til forsinkelse på togavgang");
      System.out.println("4. Søk etter togavgang (Tognummer)");
      System.out.println("5. Søk etter togavgang (Destinasjon)");
      System.out.println("6. Oppdater klokken");
      System.out.println("7. Avslutt programmet");

      choice = processChoice();

      controller.processChoice(choice);

    } while (choice != 7);
  }

  /**
   * This method prompts the user for their choice and returns it.
   *
   * @return The user's choice as an integer.
   */
  public int processChoice() {
    System.out.println("Enter your choice: ");
    while (!scanner.hasNextInt()) {
      System.out.println("That's not a number! Try again:");
      scanner.next();
    }
    int choice = scanner.nextInt();
    scanner.nextLine(); // consume the remaining newline
    return choice;
  }

  /**
   * This method starts the menu interaction by calling the init method.
   * After the init method returns, it closes the scanner.
   *
   * @param controller The TrainDispatchController that will handle the user's choice.
   */
  public void start(TrainDispatchController controller) {
    init(controller);
    scanner.close();
  }

  public void clearScreen() {
    for (int i = 0; i < 100; i++) {
      System.out.println();
    }
  }
}
