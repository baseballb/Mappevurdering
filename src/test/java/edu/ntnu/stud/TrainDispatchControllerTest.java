package edu.ntnu.stud;

import org.junit.jupiter.api.Test;

import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;

class TrainDispatchControllerTest {
  @Test
  void testProcessChoice() {
    TrainDispatchSystem trainDispatchSystem = new TrainDispatchSystem();
    Scanner scanner = new Scanner(System.in);
    TrainDispatchController controller = new TrainDispatchController(trainDispatchSystem, scanner);
    controller.processChoice(1);
    // Add assertions to verify the expected behavior
  }
}