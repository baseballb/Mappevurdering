package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.List;
public class Logic {

  private UserInteraction userInteraction;
  // userInteraction.readInput() object to read user input
  private TrainDispatchSystem trainDispatchSystem;

  public Logic(TrainDispatchSystem trainDispatchSystem, UserInteraction userInteraction) {
    this.trainDispatchSystem = trainDispatchSystem;
    this.userInteraction = userInteraction;
  }

  public boolean doesTrainExist(int trainId) {
    return trainDispatchSystem.getTrainDepartureBasedOnId(trainId) != null;
  }

  public void processChoice(int choice) {
    int trainId;
    String destination;
    TrainDeparture trainDeparture;
    switch (choice) {
      case 1:
        // Case 1: Add a new train departure
        userInteraction.displayMessage("Legg til ny togavgang");

        // Prompt for train ID that does not exist
        trainId = userInteraction.promptForTrainId(false);

        // Prompt for line
        String line = userInteraction.promptForLine();

        // Prompt for departure time
        String departureTime = userInteraction.promptForTime();

        // Prompt for destination
        destination = userInteraction.promptForDestination();

        // Add the train departure
        try {
          trainDispatchSystem.addTrainDeparture(trainId, line, departureTime, destination);
          userInteraction.displayMessage("Togavgang lagt til!");
        } catch (IllegalArgumentException e) {
          userInteraction.displayMessage("Klarte ikke å legge til togavgang: " + e.getMessage());
        }
        userInteraction.waitForEnter();
        break;
      case 2:
        // Case 2: Assign a track to a train
        userInteraction.displayMessage("Tildel spor til togavgang");

        // Prompt for existing train ID
        trainId = userInteraction.promptForTrainId(true);

        // Prompt for track number
        int trackNumber;
        do {
          userInteraction.displayMessage("Enter track number: ");
          trackNumber = userInteraction.readInt();
          userInteraction.consumeNewline();

          if (trackNumber <= 0) {
            userInteraction.displayMessage("Spor må være et positivt tall.");
            continue;
          }

          break;
        } while (true);

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
        userInteraction.displayMessage("Legg til forsinkelse på togavgang");

        // Prompt for existing train ID
        trainId = userInteraction.promptForTrainId(true);

        // Prompt for delay time
        String delayTime = userInteraction.promptForTime();

        // Add delay to train
        try {
          trainDispatchSystem.addDelayToTrain(trainId, delayTime);
          userInteraction.displayMessage("Delay added successfully.");
        } catch (IllegalArgumentException e) {
          userInteraction.displayMessage("Error adding delay: " + e.getMessage());
        } finally {
          userInteraction.waitForEnter();
        }
        break;
      case 4:
        // Case 4: Search for a train departure based on train ID
        userInteraction.displayMessage("Søk etter togavgang (Tognummer)");

        // Prompt the user for a train ID and ensure it exists
        trainId = userInteraction.promptForTrainId(true);

        // Get the TrainDeparture object with the given train ID
        trainDeparture = trainDispatchSystem.getTrainDepartureBasedOnId(trainId);

        // If no TrainDeparture object was found, print an error message
        if (trainDeparture == null) {
          userInteraction.displayMessage("No train departure found with the provided ID");
        } else {
          // If a TrainDeparture object was found, format it as a table row and print it
          String formattedTrain = trainDispatchSystem.formatTrainDeparture(trainDeparture);
          userInteraction.displayMessage(formattedTrain);
        }

        // Wait for the user to press Enter before continuing
        userInteraction.waitForEnter();
        break;
      case 5:
        // Case 5: Search for train departures based on destination
        userInteraction.displayMessage("Search for train departures based on destination");
        destination = userInteraction.promptForDestination();
        List<TrainDeparture> trainsToDestination;
        trainsToDestination = trainDispatchSystem.getTrainDeparturesBasedOnDestination(destination);
        if (trainsToDestination.isEmpty()) {
          userInteraction.displayMessage("No train departures found for the provided destination");
        } else {
          userInteraction.displayMessage(trainDispatchSystem.formatTrainsTableFormat(trainsToDestination));
        }
        userInteraction.waitForEnter();
        break;
      case 6:
        // Case 6: Update current time
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
        userInteraction.displayMessage("Ugyldig valg. Skriv inn et tall mellom 1 og 8.");
        break;
    }
  }

}
