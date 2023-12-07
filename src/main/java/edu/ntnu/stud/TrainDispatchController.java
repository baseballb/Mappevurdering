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
      case 1 -> System.out.println("You've selected Option 1");
      // Implement functionality for Option 1
      case 2 -> System.out.println("You've selected Option 2");
      // Implement functionality for Option 2
      case 3 -> System.out.println("You've selected Option 3");
      // Implement functionality for Option 3
      case 4 -> System.out.println("You've selected Option 4");
      // Implement functionality for Option 4
      case 5 -> {
        System.out.println("You've selected Option 5");
        // Implement functionality for Option 5
        System.out.println("Enter train ID: ");
        String trainId = scanner.nextLine();
        TrainDeparture trainDeparture = trainDispatchSystem.getTrainDepartureBasedOnID(trainId);
        System.out.println(trainDeparture);
      }
      case 6 -> System.out.println("You've selected Option 6");
      // Implement functionality for Option 6
      case 7 -> System.out.println("You've selected Option 7");
      // Implement functionality for Option 7
      case 8 -> System.out.println("Avslutter programmet...");
      default -> System.out.println("Invalid choice. Please enter a valid option (1-8).");
    }
  }
}