package edu.ntnu.stud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The LogicTest class contains unit tests for the Logic class.
 */
public class LogicTest {
  // Instance of Logic class to be tested
  private Logic logic;
  // Instance of TrainDispatchSystem class to be used in tests
  private TrainDispatchSystem trainDispatchSystem;
  // Instance of UserInteraction class to be used in tests
  private UserInteraction userInteraction;

  /**
   * This method sets up the objects needed for the tests.
   * It is annotated with @BeforeEach to run before each test method.
   */
  @BeforeEach
  public void setUp() {
    trainDispatchSystem = new TrainDispatchSystem();
    userInteraction = new ExampleUserInteraction();
    logic = new Logic(trainDispatchSystem, userInteraction);
  }

  /**
   * This test checks that the doesTrainExist method returns false when the train does not exist.
   */
  @Test
  public void doesTrainExist_returnsFalse_whenTrainDoesNotExist() {
    assertFalse(logic.doesTrainExist(1));
  }

  /**
   * This test checks that the addTrainDeparture method adds a train departure.
   */
  @Test
  public void addTrainDeparture_addsTrainDeparture() {
    logic.addTrainDeparture(1, "Line1", "10:00", "Destination1");
    assertTrue(logic.doesTrainExist(1));
  }

  /**
   * This test checks that the processChoice method adds a train departure when the choice is 1.
   */
  @Test
  public void processChoice_addsTrainDeparture_whenChoiceIs1() {
    logic.processChoice(1);
    assertTrue(logic.doesTrainExist(1));
  }

  /**
   * This test checks that the processChoice method assigns a track to a train when the choice is 2.
   */
  @Test
  public void processChoice_assignsTrackToTrain_whenChoiceIs2() {
    logic.addTrainDeparture(1, "Line1", "10:00", "Destination1");
    logic.processChoice(2);
    TrainDeparture trainDeparture = trainDispatchSystem.getTrainDepartureBasedOnId(1);
    assertNotNull(trainDeparture.getTrackNumber());
  }

  /**
   * This test checks that the processChoice method adds a delay to a train when the choice is 3.
   */
  @Test
  public void processChoice_addsDelayToTrain_whenChoiceIs3() {
    logic.addTrainDeparture(1, "Line1", "10:00", "Destination1");
    logic.processChoice(3);
    TrainDeparture trainDeparture = trainDispatchSystem.getTrainDepartureBasedOnId(1);
    assertNotNull(trainDeparture.getDelay());
  }

  /**
   * This test checks that the processChoice method searches for a train departure when the choice is 4.
   */
  @Test
  public void processChoice_searchesForTrainDeparture_whenChoiceIs4() {
    logic.addTrainDeparture(1, "Line1", "10:00", "Destination1");
    logic.processChoice(4);
    TrainDeparture trainDeparture = trainDispatchSystem.getTrainDepartureBasedOnId(1);
    assertNotNull(trainDeparture);
  }

  /**
   * This test checks that the processChoice method searches for train departures based on destination when the choice is 5.
   */
  @Test
  public void processChoice_searchesForTrainDeparturesBasedOnDestination_whenChoiceIs5() {
    logic.addTrainDeparture(1, "Line1", "10:00", "Destination1");
    logic.processChoice(5);
    List<TrainDeparture> trainDepartures = trainDispatchSystem.getTrainDeparturesFromDestination("Destination1");
    assertFalse(trainDepartures.isEmpty());
  }

  /**
   * This test checks that the processChoice method updates the current time when the choice is 6.
   */
  @Test
  public void processChoice_updatesCurrentTime_whenChoiceIs6() {
    logic.processChoice(6);
    LocalTime updatedTime = trainDispatchSystem.getCurrentTime();
    assertEquals(LocalTime.of(10, 0), updatedTime);
  }

  /**
   * This test checks that the addTrainDeparture method adds a train successfully.
   */
  @Test
  public void addTrainDeparture_addsTrainSuccessfully() {
    logic.addTrainDeparture(1, "Line1", "10:00", "Destination1");
    assertTrue(logic.doesTrainExist(1));
  }
}