package edu.ntnu.stud;

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

      if (choice != 8) { // Does not wait for user to press Enter if choice is 8
        System.out.println("Press Enter to continue...");
        scanner.nextLine(); // wait for user to press Enter so that they can read the output
      }
    } while (choice != 8);
  }

  /**
   * This method prompts the user for their choice and returns it.
   *
   * @return The user's choice as an integer.
   */
  public int processChoice() {
    System.out.println("Enter your choice: ");
    return Integer.parseInt(scanner.nextLine());
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
}
