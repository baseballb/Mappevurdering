package edu.ntnu.stud;

/**
 * This is the main class for the train dispatch application.
 */
public class TrainDispatchApp {
  // TODO: Fill in the main method and any other methods you need.
    public static void main(String[] args) {
        TrainDispatchSystem trainDispatchSystem = new TrainDispatchSystem();
        TrainDeparture trainDeparture = new TrainDeparture("42", "L1", "12:00", "Oslo");
        trainDispatchSystem.addTrainDeparture(trainDeparture);
        System.out.println(trainDispatchSystem.getTrainDeparture("42"));
    }
}
