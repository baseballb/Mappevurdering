package edu.ntnu.stud;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * The UserInteraction class handles all interactions with the user.
 * It reads user input and displays messages to the user.
 */
public class UserInteraction {
  private Scanner scanner;
  private TrainDispatchSystem trainDispatchSystem;
  private Logic logic;

  /**
   * Constructor for the UserInteraction class.
   * Initializes the scanner used to read user input.
   */
  public UserInteraction() {
    this.scanner = new Scanner(System.in);
  }

  /**
   * Initializes the TrainDispatchSystem and Logic objects.
   */
  public void init() {
    this.trainDispatchSystem = new TrainDispatchSystem();
    this.logic = new Logic(this.trainDispatchSystem, this);
  }

  /**
   * Starts the user interaction.
   * Adds some train departures for testing and displays the menu to the user.
   */
  public void start() {
    // Add some train departures for testing
    logic.addTrainDeparture(1, "L4", "10:00", "Oslo");
    logic.addTrainDeparture(2, "R7", "15:00", "Røros");
    logic.addTrainDeparture(3, "L4", "12:00", "Oslo");
    // Display the menu to the user
    displayMenu();
  }

  /**
   * Displays the menu to the user and processes the user's choice.
   */
  public void displayMenu() {
    int choice;
    do {
      clearScreen();
      System.out.println(logic.listAllTrainsFormatted());
      System.out.println("1. Legg til ny togavgang");
      System.out.println("2. Tildele spor til togavgang");
      System.out.println("3. Legg til forsinkelse på togavgang");
      System.out.println("4. Søk etter togavgang (Tognummer)");
      System.out.println("5. Søk etter togavgang (Destinasjon)");
      System.out.println("6. Oppdater klokken");
      System.out.println("7. Avslutt programmet");
      System.out.println("Velg et alternativ:");
      // Receive input from the user
      choice = readInt();
      // Consume the newline character after reading the integer
      consumeNewline();
      // Process the user's choice
      logic.processChoice(choice);
    } while (choice != 7); // Repeat until the user chooses to exit
  }

  /**
   * Displays a message to the user.
   *
   * @param message The message to display
   */
  public void displayMessage(String message) {
    System.out.println(message);
  }

  /**
   * Reads a line of input from the user.
   *
   * @return The line of input from the user
   */
  public String readInput() {
    return scanner.nextLine();
  }

  /**
   * Reads an integer from the user.
   * If the user does not enter an integer, prompts the user to enter a number.
   *
   * @return The integer entered by the user
   */
  public int readInt() {
    int number;
    while (!scanner.hasNextInt()) {
      displayMessage("Vennligst skriv inn et tall:");
      scanner.next();
    }
    number = scanner.nextInt();
    return number;
  }

  /**
   * Consumes the newline character after reading an integer.
   */
  public void consumeNewline() {
    if (scanner.hasNextLine()) {
      scanner.nextLine();
    }
  }

  /**
   * Waits for the user to press Enter.
   */
  public void waitForEnter() {
    System.out.println("Trykk enter for å fortsette...");
    scanner.nextLine(); // wait for user to press enter
  }

  /**
   * Clears the screen by printing 100 newline characters.
   */
  public void clearScreen() {
    for (int i = 0; i < 100; i++) {
      System.out.println();
    }
  }

  /**
   * Prompts the user for a train ID.
   * Checks if a train with the given ID exists based on the shouldExist parameter.
   * If shouldExist is true and the train exists,
   * or if shouldExist is false and the train does not exist, returns the train ID.
   * Otherwise, prompts the user for a train ID again.
   *
   * @param shouldExist Whether the train should exist
   * @return The train ID
   */
  public int promptForTrainId(boolean shouldExist) {
    int trainId;
    do {
      // Prompt the user for a train ID
      System.out.println("Skriv inn tognummer: ");
      // Check if the input is an integer
      while (!scanner.hasNextInt()) {
        System.out.println("Vennligst skriv inn et tall:");
        scanner.next(); // discard the non-integer input
      }
      trainId = scanner.nextInt();
      scanner.nextLine(); // consume the remaining newline
      // Check if a train with the given ID exists
      if (shouldExist && logic.doesTrainExist(trainId)) {
        break;
      } else if (!shouldExist && logic.doesTrainExist(trainId)) {
        System.out.println("Tog med tognummer " + trainId + " finnes allerede");
        continue;
      } else if (!shouldExist && !logic.doesTrainExist(trainId)) {
        break;
      } else {
        System.out.println("Tog med tognummer " + trainId + " finnes ikke");
      }
    } while (true);
    // Return the valid train ID
    return trainId;
  }

  /**
   * This method prompts the user for a time.
   * It checks if the time is in the correct format (HH:MM) and prompts the user again if it's not.
   *
   * @return The time entered by the user.
   */
  public String promptForTime() {
    String time;
    do {
      // Prompt the user for a time
      System.out.println("Skriv inn tidspunkt (HH:MM):");
      time = scanner.nextLine();

      // Check if the time is null or empty
      if (time == null || time.isEmpty()) {
        System.out.println("Tidspunkt kan ikke være tom");
        continue;
      }

      // Try to parse the time to ensure it's in the correct format
      try {
        LocalTime.parse(time);
        break; // If the time is in the correct format, break the loop
      } catch (DateTimeParseException e) {
        // If the time is not in the correct format, print an error and reset the time to null
        System.out.println("Tidspunkt må være i formatet HH:MM");
        time = null;
      }
    } while (time == null);
    return time;
  }

  /**
   * This method prompts the user for a destination.
   * It checks if the destination is null or empty and prompts the user again if it is.
   *
   * @return The destination entered by the user.
   */
  public String promptForDestination() {
    String destination;
    do {
      // Prompt the user for a destination
      System.out.println("Skriv inn destinasjon:");
      destination = scanner.nextLine();

      // Check if the destination is null or empty
      if (destination == null || destination.isEmpty()) {
        System.out.println("Destinasjon kan ikke være tom");
      }
      // Repeat the loop until a valid destination is entered
    } while (destination == null || destination.isEmpty());
    return destination;
  }

  /**
   * This method prompts the user for a line.
   * It checks if the line is null or empty and prompts the user again if it is.
   *
   * @return The line entered by the user.
   */
  public String promptForLine() {
    String line;
    do {
      // Prompt the user for a line
      System.out.println("Skriv inn linje:");
      line = scanner.nextLine();

      // Check if the line is null or empty
      if (line == null || line.isEmpty()) {
        System.out.println("Linje kan ikke være tom");
      }
      // Repeat the loop until a valid line is entered
    } while (line == null || line.isEmpty());
    return line;
  }

  /**
   * This method prompts the user for a track number.
   * It checks if the track number is a positive integer and prompts the user again if it's not.
   *
   * @return The track number entered by the user.
   */
  public int promptForTrackNumber() {
    int trackNumber;
    do {
      // Prompt the user for a track number
      System.out.println("Skriv inn spor:");
      // Check if the input is an integer
      while (!scanner.hasNextInt()) {
        System.out.println("Spor må være et positivt tall.");
        scanner.next(); // discard the non-integer input
      }
      trackNumber = scanner.nextInt();
      scanner.nextLine(); // consume the remaining newline
      // Check if the track number is valid
      if (trackNumber < 1) {
        System.out.println("Spor må være et positivt tall.");
      }
      // Repeat the loop until a valid track number is entered
    } while (trackNumber < 1);
    return trackNumber;
  }

  /**
   * This method handles errors by displaying an error message to the user.
   *
   * @param errorMessage The error message to display
   */
  public void handleError(String errorMessage) {
    System.out.println("Feil: " + errorMessage);
  }
}