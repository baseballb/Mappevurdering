package edu.ntnu.stud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// TODO: Fix the tests in this class

class TrainDispatchSystemTest {
    private TrainDispatchSystem trainDispatchSystem;

    @BeforeEach
    void setUp() {
        trainDispatchSystem = new TrainDispatchSystem();
    }
    @Test
    void testAddTrainDepartureAddsTrainSuccessfully() {
        trainDispatchSystem.addTrainDeparture(42, "Trondheim", "12:00", "Oslo");
        TrainDeparture trainDeparture = trainDispatchSystem.getTrainDepartureBasedOnID(42);
        assertEquals(42, trainDeparture.getTrainId());
        assertEquals("Trondheim", trainDeparture.getLine());
        assertEquals(LocalTime.parse("12:00"), trainDeparture.getDepartureTime());
        assertEquals("Oslo", trainDeparture.getDestination());
    }
    @Test
    void testGetTrainDepartureBasedOnIDReturnsCorrectTrain() {
        trainDispatchSystem.addTrainDeparture(42, "Trondheim", "12:00", "Oslo");
        TrainDeparture trainDeparture = trainDispatchSystem.getTrainDepartureBasedOnID(42);
        assertEquals(42, trainDeparture.getTrainId());
        assertEquals("Trondheim", trainDeparture.getLine());
        assertEquals(LocalTime.parse("12:00"), trainDeparture.getDepartureTime());
        assertEquals("Oslo", trainDeparture.getDestination());
    }

    @Test
    void getTrainDepartureBasedOnIDReturnsNullForNonexistentTrain() {
        assertNull(trainDispatchSystem.getTrainDepartureBasedOnID(43));
    }

    @Test
    void testGetTrainDeparturesBasedOnDestinationReturnsCorrectTrain() {
        trainDispatchSystem.addTrainDeparture(42, "L4", "12:00", "Oslo");
        trainDispatchSystem.addTrainDeparture(43, "L4", "13:00", "Oslo");
        trainDispatchSystem.addTrainDeparture(44, "L4", "11:00", "Oslo");
        List<TrainDeparture> departuresForDestination = trainDispatchSystem.getTrainDeparturesBasedOnDestination("Oslo");
        assertEquals(3, departuresForDestination.size());
    }
    @Test
    void testGetTrainDeparturesBasedOnDestinationReturnsEmptyListNonexistentDestination() {
        List<TrainDeparture> departuresForDestination = trainDispatchSystem.getTrainDeparturesBasedOnDestination("Oslo");
        assertEquals(0, departuresForDestination.size());
    }

    @Test
    void testTrainDispatchSystem() {
        assertNotNull(trainDispatchSystem);
    }
    @Test // Test that adding a train departure with the same id throws an exception
    void testAddTrainDepartureThrowsException() {
        trainDispatchSystem.addTrainDeparture(42, "Trondheim", "12:00", "Oslo");
        assertThrows(IllegalArgumentException.class, () -> trainDispatchSystem.addTrainDeparture(42, "Trondheim", "12:00", "Oslo"));
    }

    @Test
    void testAssignTrainToTrackAssignsTrackSuccessfully() {
        trainDispatchSystem.addTrainDeparture(42, "Trondheim", "12:00", "Oslo");
        trainDispatchSystem.assignTrainToTrack(42, 1);
        TrainDeparture trainDeparture = trainDispatchSystem.getTrainDepartureBasedOnID(42);
        assertEquals(1, trainDeparture.getTrackNumber());
    }

    @Test
    void testAddDelayToTrainAddsDelaySuccessfully() {
        trainDispatchSystem.addTrainDeparture(42, "Trondheim", "12:00", "Oslo");
        trainDispatchSystem.addDelayToTrain(42, "01:00");
        TrainDeparture trainDeparture = trainDispatchSystem.getTrainDepartureBasedOnID(42);
        assertEquals("01:00", trainDeparture.getDelay().toString());
    }

    @Test // Tests that the listAllTrains method returns a list of all the trains in the system, sorted by departure time.
    void testListAllTrainsReturnsTrainsInCorrectOrder() {
        trainDispatchSystem.addTrainDeparture(42, "Trondheim", "12:00", "Oslo");
        trainDispatchSystem.addTrainDeparture(43, "Trondheim", "13:00", "Oslo");
        trainDispatchSystem.addTrainDeparture(44, "Trondheim", "11:00", "Oslo");
        List<TrainDeparture> trainDepartures = trainDispatchSystem.listAllTrains();
        assertEquals(44, trainDepartures.get(0).getTrainId());
        assertEquals(42, trainDepartures.get(1).getTrainId());
        assertEquals(43, trainDepartures.get(2).getTrainId());
    }



}