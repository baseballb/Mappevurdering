// UserInteraction.java
package edu.ntnu.stud;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class UserInteraction {
  private Scanner scanner;
  private TrainDispatchSystem trainDispatchSystem;
  private Logic logic;

  public UserInteraction() {
    this.scanner = new Scanner(System.in);
  }

  public void init() {
    this.trainDispatchSystem = new TrainDispatchSystem();
    this.logic = new Logic(this.trainDispatchSystem, this);
  }

  public void start() {
    // Add some train departures for testing
    logic.addTrainDeparture(1, "L4", "10:00", "Oslo");
    logic.addTrainDeparture(2, "R7", "11:00", "Røros");
    logic.addTrainDeparture(3, "L4", "12:00", "Oslo");
    displayMenu();
  }

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
      choice = readInt();
      consumeNewline();
      logic.processChoice(choice);
    } while (choice != 7);

  }

  public void displayMessage(String message) {
    System.out.println(message);
  }

  public String readInput() {
    return scanner.nextLine();
  }

  public int readInt() {
    while (!scanner.hasNextInt()) {
      displayMessage("Please enter a number:");
      scanner.next();
    }
    return scanner.nextInt();
  }

  public void consumeNewline() {
    if (scanner.hasNextLine()) {
      scanner.nextLine();
    }
  }

  public void waitForEnter() {
    System.out.println("Trykk enter for å fortsette...");
    scanner.nextLine(); // wait for user to press enter
  }

  public void clearScreen() {
    for (int i = 0; i < 100; i++) {
      System.out.println();
    }
  }

  public int promptForTrainId(boolean shouldExist) {
    int trainId;
    do {
      // Prompt the user for a train ID
      System.out.println("Enter train ID: ");
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
          System.out.println("Train with ID " + trainId + " already exists");
          continue;
        } else if (!shouldExist && !logic.doesTrainExist(trainId)) {
          break;
        } else {
          System.out.println("Train with ID " + trainId + " does not exist");
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
      System.out.println("Enter time (HH:MM): ");
      time = scanner.nextLine();

      // Check if the time is null or empty
      if (time == null || time.isEmpty()) {
        System.out.println("Time cannot be null or empty");
        continue;
      }

      // Try to parse the time to ensure it's in the correct format
      try {
        LocalTime.parse(time);
        break; // If the time is in the correct format, break the loop
      } catch (DateTimeParseException e) {
        // If the time is not in the correct format, print an error and reset the time to null
        System.out.println("Invalid time format. It should be HH:MM");
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
        System.out.println("Destination cannot be null or empty");
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
      System.out.println("Enter line: ");
      line = scanner.nextLine();

      // Check if the line is null or empty
      if (line == null || line.isEmpty()) {
        System.out.println("Line cannot be null or empty");
      }
      // Repeat the loop until a valid line is entered
    } while (line == null || line.isEmpty());
    return line;
  }

  public void handleError(String errorMessage) {
    System.out.println("Error: " + errorMessage);
  }
}