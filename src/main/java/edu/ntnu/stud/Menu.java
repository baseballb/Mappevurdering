package edu.ntnu.stud;

import java.util.Scanner;

public class Menu {
    public static void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Welcome to the Text-Based Menu");
            System.out.println("1. Vis oversikt over togavganger");
            System.out.println("2. Legg til ny togavgang");
            System.out.println("3. Tildele spor til togavgang");
            System.out.println("4. Legg til forsinkelse på togavgang");
            System.out.println("5. Søk etter togavgang (Tognummer)");
            System.out.println("6. Søk etter togavgang (Destinasjon)");
            System.out.println("7. Oppdater klokken");
            System.out.println("8. Avslutt programmet");
            System.out.print("Enter your choice: ");

            // Read user input
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("You've selected Option 1");
                    // Implement functionality for Option 1
                    break;
                case 2:
                    System.out.println("You've selected Option 2");
                    // Implement functionality for Option 2
                    break;
                case 3:
                    System.out.println("You've selected Option 3");
                    // Implement functionality for Option 3
                    break;
                case 4:
                    System.out.println("You've selected Option 4");
                    // Implement functionality for Option 4
                    break;
                case 5:
                    System.out.println("You've selected Option 5");
                    // Implement functionality for Option 5
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
                    System.out.println("Invalid choice. Please enter a valid option (1-4).");
                    break;
            }
        } while (choice != 4);

        scanner.close();
    }
}
