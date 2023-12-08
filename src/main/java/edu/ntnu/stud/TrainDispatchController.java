package edu.ntnu.stud;

import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.time.LocalTime;

/**
 * The TrainDispatchController class is responsible for handling user input and controlling the application flow.
 * It uses a Scanner to read user input and calls methods on the TrainDispatchSystem class based on this input.
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
    switch (choice) {
      case 1:
        System.out.println("Vis oversikt over togavganger");
        System.out.println(trainDispatchSystem.listAllTrains());
        break;
      case 2:
        System.out.println("Legg til ny togavgang");
        System.out.println("Enter train ID: ");
        while (!scanner.hasNextInt()) {
          System.out.println("That's not a number! Try again:");
          scanner.next();
        }
        int trainId = scanner.nextInt();
        scanner.nextLine(); // Consume the remaining newline
        System.out.println("Enter line: ");
        String line = scanner.nextLine();
        if (line == null || line.isEmpty()) {
          System.out.println("Line cannot be null or empty");
          return;
        }
        System.out.println("Enter departure time (HH:MM): ");
        String departureTime = scanner.nextLine();
        if (departureTime == null || departureTime.isEmpty()) {
          System.out.println("Avgangstid kan ikke være null eller tom");
          return;
        }
        try {
          LocalTime.parse(departureTime);
        } catch (DateTimeParseException e) {
          System.out.println("Ugyldig avgangstid. Skriv inn avgangstid på formatet HH:MM");
          return;
        }
        System.out.println("Skriv inn destinasjon:");
        String destination = scanner.nextLine();
        if (destination == null || destination.isEmpty()) {
          System.out.println("Destinasjon kan ikke være null eller tom");
          return;
        }
        try {
          trainDispatchSystem.addTrainDeparture(trainId, line, departureTime, destination);
          System.out.println("Togavgang lagt til!");
        } catch (IllegalArgumentException e) {
          System.out.println("Klarte ikke å legge til togavgang: " + e.getMessage());
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
        System.out.println("Skriv inn tognummer: ");
        while (!scanner.hasNextInt()) {
          System.out.println("Vennligst skriv inn et tall: ");
          scanner.next(); // discard the non-integer input
        }
        int trainId_input = scanner.nextInt();
        scanner.nextLine(); // consume the remaining newline
        TrainDeparture trainDeparture = trainDispatchSystem.getTrainDepartureBasedOnID(trainId_input);
        if (trainDeparture == null) {
          System.out.println("Fant ingen togavgang med tognummer " + trainId_input + ".");
        } else {
          System.out.println(trainDeparture);
        }
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
        System.out.println("Ugyldig valg. Skriv inn et tall mellom 1 og 8.");
        break;
    }
  }
}