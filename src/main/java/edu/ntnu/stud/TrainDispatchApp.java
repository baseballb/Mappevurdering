package edu.ntnu.stud;

public class TrainDispatchApp {
  public static void main(String[] args) {
    TrainDispatchSystem trainDispatchSystem = new TrainDispatchSystem();
    Menu menu = new Menu(trainDispatchSystem);
    menu.init();
    menu.start();
  }
}