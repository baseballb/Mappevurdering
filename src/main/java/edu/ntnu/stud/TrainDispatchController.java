package edu.ntnu.stud;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * The TrainDispatchController class is responsible for handling user input
 * and controlling the application flow. It uses a Scanner to read user input
 * and calls methods on the TrainDispatchSystem class based on this input.
 */
public class TrainDispatchController {
  private TrainDispatchSystem trainDispatchSystem;
  private Scanner scanner;

  /**
   * Constructor for the TrainDispatchController class.
   * Initializes the TrainDispatchSystem and Scanner objects.
   *
   * @param trainDispatchSystem The TrainDispatchSystem that this controller will interact with.
   * @param scanner The Scanner that this controller will use to read user input.
   */
  public TrainDispatchController(TrainDispatchSystem trainDispatchSystem, Scanner scanner) {
    this.trainDispatchSystem = trainDispatchSystem;
    this.scanner = scanner;
  }

  /**
   * This method processes the user's choice.
   * It reads the user's choice as an integer, and calls the appropriate
   * method on the TrainDispatchSystem based on this choice.
   *
   * @param choice The user's choice as an integer.
   */
  public void processChoice(int choice) {
    int trainId;
    String destination;
    TrainDeparture trainDeparture;
    switch (choice) {
      case 1:
        // Case 1: Add a new train departure
        System.out.println("Legg til ny togavgang");

        // Prompt for train ID that does not exist
        trainId = promptForTrainId(false);

        // Prompt for line
        String line = promptForLine();

        // Prompt for departure time
        String departureTime = promptForTime();

        // Prompt for destination
        destination = promptForDestination();

        // Add the train departure
        try {
          trainDispatchSystem.addTrainDeparture(trainId, line, departureTime, destination);
          System.out.println("Togavgang lagt til!");
        } catch (IllegalArgumentException e) {
          System.out.println("Klarte ikke å legge til togavgang: " + e.getMessage());
        }
        waitForEnter();
        break;
      case 2:
        // Case 2: Assign a track to a train
        System.out.println("Tildel spor til togavgang");

        // Prompt for existing train ID
        trainId = promptForTrainId(true);

        // Prompt for track number
        int trackNumber;
        do {
          System.out.println("Enter track number: ");
          while (!scanner.hasNextInt()) {
            System.out.println("Vennligst skriv inn et tall:");
            scanner.next(); // discard the non-integer input
          }
          trackNumber = scanner.nextInt();
          scanner.nextLine(); // consume the remaining newline
        } while (trackNumber <= 0);

        // Assign track to train
        try {
          trainDispatchSystem.assignTrainToTrack(trainId, trackNumber);
          System.out.println("Togavgang tildelt spor!");
        } catch (IllegalArgumentException e) {
          System.out.println("Kunne ikke tildele spor: " + e.getMessage());
        } finally {
          waitForEnter();
        }
        break;
      case 3:
        // Case 3: Add a delay to a train
        System.out.println("Legg til forsinkelse på togavgang");

        // Prompt for existing train ID
        trainId = promptForTrainId(true);

        // Prompt for delay time
        String delayTime = promptForTime();

        // Add delay to train
        try {
          trainDispatchSystem.addDelayToTrain(trainId, delayTime);
          System.out.println("Delay added successfully.");
        } catch (IllegalArgumentException e) {
          System.out.println("Error adding delay: " + e.getMessage());
        } finally {
          waitForEnter();
        }
        break;
      case 4:
        // Case 4: Search for a train departure based on train ID
        System.out.println("Søk etter togavgang (Tognummer)");

        // Prompt the user for a train ID and ensure it exists
        trainId = promptForTrainId(true);

        // Get the TrainDeparture object with the given train ID
        trainDeparture = trainDispatchSystem.getTrainDepartureBasedOnId(trainId);

        // If no TrainDeparture object was found, print an error message
        if (trainDeparture == null) {
          System.out.println("No train departure found with the provided ID");
        } else {
          // If a TrainDeparture object was found, format it as a table row and print it
          String formattedTrain = trainDispatchSystem.formatTrainDeparture(trainDeparture);
          System.out.println(formattedTrain);
        }

        // Wait for the user to press Enter before continuing
        waitForEnter();
        break;
      case 5:
        // Case 5: Search for train departures based on destination
        System.out.println("Search for train departures based on destination");
        destination = promptForDestination();
        List<TrainDeparture> trainsToDestination;
        trainsToDestination = trainDispatchSystem.getTrainDeparturesBasedOnDestination(destination);
        if (trainsToDestination.isEmpty()) {
          System.out.println("No train departures found for the provided destination");
        } else {
          System.out.println(trainDispatchSystem.formatTrainsTableFormat(trainsToDestination));
        }
        waitForEnter();
        break;
      case 6:
        // Case 6: Update current time
        System.out.println("Update current time");
        String newTimeStr = promptForTime();
        try {
          trainDispatchSystem.setCurrentTime(LocalTime.parse(newTimeStr));
          System.out.println("Current time updated successfully.");
        } catch (IllegalArgumentException e) {
          System.out.println("Error updating current time: " + e.getMessage());
        }
        waitForEnter();
        break;
      case 7:
        // Case 7: Exit the program
        System.out.println("Avslutter programmet...");
        break;
      default:
        // Default case: Invalid choice
        System.out.println("Ugyldig valg. Skriv inn et tall mellom 1 og 8.");
        break;
    }
  }

  /**
   * Getter for the TrainDispatchSystem instance.
   *
   * @return The TrainDispatchSystem instance that this controller interacts with.
   */
  public TrainDispatchSystem getTrainDispatchSystem() {
    return trainDispatchSystem;
  }

  /**
   * This method prints a message and waits for the user to press Enter.
   */
  public void waitForEnter() {
    System.out.println("Trykk enter for å fortsette...");
    scanner.nextLine(); // wait for user to press enter
  }

  /**
   * This method prompts the user for a train ID.
   * It checks if a train with the given ID exists and prompts the user again if it doesn't.
   *
   * @param shouldExist A boolean indicating whether the train ID should exist.
   * @return The train ID entered by the user.
   */
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
      TrainDeparture trainDeparture = trainDispatchSystem.getTrainDepartureBasedOnId(trainId);
      // If the train ID should exist but doesn't, prompt the user again
      if (shouldExist && trainDeparture == null) {
        System.out.println("No train departure found with the provided ID");
        continue;
        // If the train ID should not exist but does, prompt the user again
      } else if (!shouldExist && trainDeparture != null) {
        System.out.println("A train departure with the provided ID already exists");
        continue;
      }
      // If the input is valid, break the loop
      break;
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
      } catch (DateTimeParseException e) {
        // If the time is not in the correct format, print an error and reset the time to null
        System.out.println("Invalid time format. It should be HH:MM");
        time = null;
      }
      // Repeat the loop until a valid time is entered
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
}