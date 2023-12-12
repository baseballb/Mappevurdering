package edu.ntnu.stud;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * The Logic class handles the logic of the application.
 * It interacts with the UserInteraction and TrainDispatchSystem classes.
 */
public class Logic {

  // UserInteraction object to read user input
  private UserInteraction userInteraction;

  // TrainDispatchSystem object to interact with the train dispatch system
  private TrainDispatchSystem trainDispatchSystem;

  /**
   * Constructor for the Logic class.
   *
   * @param trainDispatchSystem The TrainDispatchSystem object
   * @param userInteraction The UserInteraction object
   */
  public Logic(TrainDispatchSystem trainDispatchSystem, UserInteraction userInteraction) {
    this.trainDispatchSystem = trainDispatchSystem;
    this.userInteraction = userInteraction;
  }

  /**
   * Checks if a train with the given ID exists.
   *
   * @param trainId The ID of the train
   * @return true if the train exists, false otherwise
   */
  public boolean doesTrainExist(int trainId) {
    return trainDispatchSystem.getTrainDepartureBasedOnId(trainId) != null;
  }

  /**
   * Processes the user's choice.
   *
   * @param choice The user's choice
   */
  public void processChoice(int choice) {
    int trainId;
    String destination;
    TrainDeparture trainDeparture;
    try {
      switch (choice) {
        case 1:
          // Case 1: Add a new train departure
          // The following steps are performed:
          // 1. Prompt for train ID that does not exist
          // 2. Prompt for line
          // 3. Prompt for departure time
          // 4. Prompt for destination
          // 5. Add the train departure
          // 6. Display a success message or an error message
          // 7. Wait for the user to press Enter before continuing

          userInteraction.displayMessage("Legg til ny togavgang");

          trainId = userInteraction.promptForTrainId(false);

          String line = userInteraction.promptForLine();

          String departureTime = userInteraction.promptForTime();

          destination = userInteraction.promptForDestination();

          try {
            trainDispatchSystem.addTrainDeparture(trainId, line, departureTime, destination);
            userInteraction.displayMessage("Togavgang lagt til!");
          } catch (IllegalArgumentException e) {
            userInteraction.handleError(e.getMessage());
          }
          userInteraction.waitForEnter();
          break;
        case 2:
          // Case 2: Assign a track to a train
          // The following steps are performed:
          // 1. Prompt for existing train ID
          // 2. Prompt for track number
          // 3. Assign track to train
          // 4. Display a success message or an error message
          // 5. Wait for the user to press Enter before continuing

          userInteraction.displayMessage("Tildel spor til togavgang");

          // Prompt for existing train ID
          trainId = userInteraction.promptForTrainId(true);

          // Prompt for track number
          int trackNumber = userInteraction.promptForTrackNumber();

          // Assign track to train
          try {
            trainDispatchSystem.assignTrainToTrack(trainId, trackNumber);
            userInteraction.displayMessage("Togavgang tildelt spor!");
          } catch (IllegalArgumentException e) {
            userInteraction.displayMessage("Kunne ikke tildele spor: " + e.getMessage());
          } finally {
            userInteraction.waitForEnter();
          }
          break;
        case 3:
          // Case 3: Add a delay to a train
          // The following steps are performed:
          // 1. Prompt for existing train ID
          // 2. Prompt for delay time
          // 3. Add delay to train
          // 4. Display a success message or an error message
          // 5. Wait for the user to press Enter before continuing

          userInteraction.displayMessage("Legg til forsinkelse på togavgang");

          trainId = userInteraction.promptForTrainId(true);

          String delayTime = userInteraction.promptForTime();

          try {
            trainDispatchSystem.addDelayToTrain(trainId, delayTime);
            userInteraction.displayMessage("Forsinkelse lagt til!");
          } catch (IllegalArgumentException e) {
            userInteraction.displayMessage("Kunne ikke legge til forsinkelse: " + e.getMessage());
          } finally {
            userInteraction.waitForEnter();
          }
          break;
        case 4:
          // Case 4: Search for a train departure based on train ID
          // The following steps are performed:
          // 1. Prompt for train ID
          // 2. Get the TrainDeparture object with the given train ID
          // 3. If no TrainDeparture object was found, print an error message
          // 4. If a TrainDeparture object was found, format it as a table row and print it
          // 5. Wait for the user to press Enter before continuing

          userInteraction.displayMessage("Søk etter togavgang (Tognummer)");

          trainId = userInteraction.promptForTrainId(true);

          trainDeparture = trainDispatchSystem.getTrainDepartureBasedOnId(trainId);

          if (trainDeparture == null) {
            userInteraction.displayMessage("No train departure found with the provided ID");
          } else {
            String formattedTrain = trainDispatchSystem.formatTrainDeparture(trainDeparture);
            userInteraction.displayMessage(formattedTrain);
          }

          userInteraction.waitForEnter();
          break;
        case 5:
          // Case 5: Search for train departures based on destination
          // The following steps are performed:
          // 1. Prompt for destination
          // 2. Get a list of TrainDeparture objects with the given destination
          // 3. If no TrainDeparture objects were found, print an error message
          // 4. If TrainDeparture objects were found, format them as a table and print it
          // 5. Wait for the user to press Enter before continuing

          userInteraction.displayMessage("Søk etter togavgang (Destinasjon)");
          destination = userInteraction.promptForDestination();
          List<TrainDeparture> trainsToDestination;
          trainsToDestination = trainDispatchSystem.getTrainDeparturesFromDestination(destination);
          if (trainsToDestination.isEmpty()) {
            userInteraction.displayMessage("Ingen togavganger funnet til destinasjonen");
          } else {
            userInteraction.displayMessage(formatTrainsToTable(trainsToDestination));
          }
          userInteraction.waitForEnter();
          break;
        case 6:
          // Case 6: Update current time
          // The following steps are performed:
          // 1. Prompt for new current time
          // 2. Update current time
          // 3. Display a success message or an error message
          // 4. Wait for the user to press Enter before continuing

          userInteraction.displayMessage("Update current time");
          String newTimeStr = userInteraction.promptForTime();
          try {
            trainDispatchSystem.setCurrentTime(LocalTime.parse(newTimeStr));
            userInteraction.displayMessage("Current time updated successfully.");
          } catch (IllegalArgumentException e) {
            userInteraction.displayMessage("Error updating current time: " + e.getMessage());
          }
          userInteraction.waitForEnter();
          break;
        case 7:
          // Case 7: Exit the program
          userInteraction.displayMessage("Avslutter programmet...");
          break;
        default:
          // Default case: Invalid choice
          throw new IllegalArgumentException("Ugyldig valg. Velg et tall mellom 1 og 7.");
      }
    } catch (Exception e) { // Catch all exceptions
      userInteraction.handleError(e.getMessage());
      userInteraction.waitForEnter();

    }
  }

  /**
   * Adds a new train departure.
   * Used for testing purposes,
   * since the user is not prompted for input at start()
   *
   * @param trainId The ID of the train
   * @param line The line of the train
   * @param departureTime The departure time of the train
   * @param destination The destination of the train
   */
  public void addTrainDeparture(int trainId, String line,
                                String departureTime, String destination) {
    if (destination == null || destination.isEmpty()) {
      throw new IllegalArgumentException("Destination cannot be null or empty");
    }
    try {
      LocalTime.parse(departureTime); // Validate the departure time format
      trainDispatchSystem.addTrainDeparture(trainId, line, departureTime, destination);
    } catch (DateTimeParseException e) {
      throw new IllegalArgumentException("Invalid departure time format. It should be HH:MM");
    } catch (Exception e) {
      userInteraction.handleError(e.getMessage());
    }
  }

  /**
   * Lists all trains in a formatted string.
   *
   * @return A string containing all trains in a formatted table
   */
  public String listAllTrainsFormatted() {
    return trainDispatchSystem.formatTrainsToTable(trainDispatchSystem.listAllTrains());
  }

  /**
   * Formats a list of trains to a table.
   *
   * @param trains The list of trains to format
   * @return A string containing the trains in a formatted table
   */
  public String formatTrainsToTable(List<TrainDeparture> trains) {
    return trainDispatchSystem.formatTrainsToTable(trains);
  }

}
