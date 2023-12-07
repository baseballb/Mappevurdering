package edu.ntnu.stud;

import java.util.Scanner;

/**
 * This is the main class for the train dispatch application.
 */
public class TrainDispatchApp {
  // TODO: Fill in the main method and any other methods you need.
    public static void main(String[] args) {
        TrainDispatchSystem trainDispatchSystem = new TrainDispatchSystem();
        Scanner scanner = new Scanner(System.in);
        TrainDispatchController controller = new TrainDispatchController(trainDispatchSystem, scanner);
        Menu menu = new Menu();
        menu.start(controller);


    }
}
