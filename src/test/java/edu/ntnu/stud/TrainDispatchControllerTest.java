package edu.ntnu.stud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TrainDispatchControllerTest {
  private TrainDispatchSystem trainDispatchSystem;
  private TrainDispatchController controller;
  private Scanner scanner;

  @BeforeEach
  void setUp() {
    trainDispatchSystem = new TrainDispatchSystem();
  }

  @Test
  void processChoiceAddsTrainDeparture() {
    String input = "42\nTrondheim\n12:00\nOslo\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    scanner = new Scanner(in);
    controller = new TrainDispatchController(trainDispatchSystem, scanner);

    controller.processChoice(2);

    assertNotNull(controller.getTrainDispatchSystem().getTrainDepartureBasedOnID(42));
  }

  @Test
  void processChoiceAssignsTrackToTrain() {
    trainDispatchSystem.addTrainDeparture(42, "Trondheim", "12:00", "Oslo");
    String input = "42\n1\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    scanner = new Scanner(in);
    controller = new TrainDispatchController(trainDispatchSystem, scanner);

    controller.processChoice(3);

    assertEquals(1, controller.getTrainDispatchSystem().getTrainDepartureBasedOnID(42).getTrackNumber());
  }

  @Test
  void processChoiceAddsDelayToTrain() {
    trainDispatchSystem.addTrainDeparture(42, "Trondheim", "12:00", "Oslo");
    String input = "42\n01:00\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    scanner = new Scanner(in);
    controller = new TrainDispatchController(trainDispatchSystem, scanner);

    controller.processChoice(4);

    assertEquals("01:00", controller.getTrainDispatchSystem().getTrainDepartureBasedOnID(42).getDelay().toString());
  }

  @Test
  void processChoiceSearchesTrainDeparture() {
    trainDispatchSystem.addTrainDeparture(42, "Trondheim", "12:00", "Oslo");
    String input = "42\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    scanner = new Scanner(in);
    controller = new TrainDispatchController(trainDispatchSystem, scanner);

    controller.processChoice(5);

    assertNotNull(controller.getTrainDispatchSystem().getTrainDepartureBasedOnID(42));
  }

  @Test
  void processChoiceSearchesTrainDeparturesBasedOnDestination() {
    trainDispatchSystem.addTrainDeparture(42, "Trondheim", "12:00", "Oslo");
    String input = "Oslo\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    scanner = new Scanner(in);
    controller = new TrainDispatchController(trainDispatchSystem, scanner);

    controller.processChoice(6);

    assertFalse(controller.getTrainDispatchSystem().getTrainDeparturesBasedOnDestination("Oslo").isEmpty());
  }

  @Test
  void processChoiceUpdatesClock() {
    String input = "06:00\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    scanner = new Scanner(in);
    controller = new TrainDispatchController(trainDispatchSystem, scanner);

    controller.processChoice(7);

    assertEquals("06:00", controller.getTrainDispatchSystem().getCurrentTime().toString());
  }
}