package edu.ntnu.stud;

/**
 * The TrainDispatchApp class contains the main method that starts the application.
 */
public class TrainDispatchApp {
  /**
   * The main method of the application.
   * It creates a new UserInteraction object, initializes it, and starts the user interaction.
   *
   * @param args The command-line arguments
   */
  public static void main(String[] args) {
    UserInteraction UserInteraction = new UserInteraction();
    UserInteraction.init();
    UserInteraction.start();
  }
}