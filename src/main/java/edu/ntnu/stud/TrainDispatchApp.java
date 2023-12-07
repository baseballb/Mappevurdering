package edu.ntnu.stud;

import java.util.Scanner;

/**
 * This class is the entry point for the application.
 * it creates a TrainDispatchSystem and a Scanner object,
 * and passes them to a TrainDispatchController.
 * This setup allows the TrainDispatchController to handle
 * user input and control the application flow,
 * while the TrainDispatchSystem manages TrainDeparture instances.
 */
public class TrainDispatchApp {
  /**
   * The main method is the entry point of the application.
   * It creates a TrainDispatchSystem, a Scanner, and a TrainDispatchController,
   * and starts the menu interaction.
   *
   * @param args command-line arguments (not used)
   */
  public static void main(String[] args) {
    // Create a new TrainDispatchSystem that we can pass to the TrainDispatchController
    TrainDispatchSystem trainDispatchSystem = new TrainDispatchSystem();

    // Create a new Scanner for reading user input that we can pass to the TrainDispatchController
    Scanner scanner = new Scanner(System.in);

    // Create a new TrainDispatchController, passing it the TrainDispatchSystem and Scanner
    TrainDispatchController controller = new TrainDispatchController(trainDispatchSystem, scanner);

    // Create a new Menu
    Menu menu = new Menu();

    // Start the menu interaction, passing it the TrainDispatchController
    menu.start(controller);
  }
}
