package edu.ntnu.stud;

import java.util.Scanner;

public class TrainDispatchController {
  private TrainDispatchSystem trainDispatchSystem;
  private Scanner scanner;

  public TrainDispatchController(TrainDispatchSystem trainDispatchSystem, Scanner scanner) {
    this.trainDispatchSystem = trainDispatchSystem;
    this.scanner = scanner;
  }

  public void processChoice(int choice) {
    switch (choice) {
      case 1:
        System.out.println("Vis oversikt over togavganger");
        System.out.println(trainDispatchSystem.listAllTrains());
        break;
      case 2:
        System.out.println("Legg til ny togavgang");
        System.out.println("Enter train ID: ");
        String trainId = scanner.nextLine();
        System.out.println("Enter line: ");
        String line = scanner.nextLine();
        System.out.println("Enter departure time (HH:MM): ");
        String departureTime = scanner.nextLine();
        System.out.println("Enter destination: ");
        String destination = scanner.nextLine();
        try {
          trainDispatchSystem.addTrainDeparture(trainId, line, departureTime, destination);
          System.out.println("Train departure added successfully.");
        } catch (IllegalArgumentException e) {
          System.out.println("Error adding train departure: " + e.getMessage());
        }
        break;
      case 3:
        System.out.println("Tildel spor til togavgang");
        // Implement functionality for Option 3
        break;
      case 4:
        System.out.println("Legg til forsinkelse på togavgang");
        // Implement functionality for Option 4
        break;
      case 5:
        System.out.println("Søk etter togavgang (Tognummer)");
        // Implement functionality for Option 5
        System.out.println("Enter train ID: ");
        String trainId_input = scanner.nextLine();
        TrainDeparture trainDeparture = trainDispatchSystem.getTrainDepartureBasedOnID(trainId_input);
        System.out.println(trainDeparture);
        break;
      case 6:
        System.out.println("You've selected Option 6");
        // Implement functionality for Option 6
        break;
      case 7:
        System.out.println("You've selected Option 7");
        // Implement functionality for Option 7
        break;
      case 8:
        System.out.println("Avslutter programmet...");
        break;
      default:
        System.out.println("Invalid choice. Please enter a valid option (1-8).");
        break;
    }
  }
}