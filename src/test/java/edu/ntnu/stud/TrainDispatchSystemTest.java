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
    void testAddTrainDeparture() {
        trainDispatchSystem.addTrainDeparture(42, "Trondheim", "12:00", "Oslo");
        TrainDeparture trainDeparture = trainDispatchSystem.getTrainDepartureBasedOnID(42);
        assertEquals(42, trainDeparture.getTrainId());
        assertEquals("Trondheim", trainDeparture.getLine());
        assertEquals(LocalTime.parse("12:00"), trainDeparture.getDepartureTime());
        assertEquals("Oslo", trainDeparture.getDestination());
    }
    @Test
    void testGetTrainDepartureBasedOnID() {
        trainDispatchSystem.addTrainDeparture(42, "Trondheim", "12:00", "Oslo");
        TrainDeparture trainDeparture = trainDispatchSystem.getTrainDepartureBasedOnID(42);
        assertEquals(42, trainDeparture.getTrainId());
        assertEquals("Trondheim", trainDeparture.getLine());
        assertEquals(LocalTime.parse("12:00"), trainDeparture.getDepartureTime());
        assertEquals("Oslo", trainDeparture.getDestination());
    }

    @Test
    void testGetTrainDepartureBasedOnIDReturnsNull() {
        trainDispatchSystem.addTrainDeparture(42, "Trondheim", "12:00", "Oslo");
        assertNull(trainDispatchSystem.getTrainDepartureBasedOnID(43));
    }
    @Test
    void testGetTrainDepartureBasedOnIDReturnsNullWhenEmpty() {
        assertNull(trainDispatchSystem.getTrainDepartureBasedOnID(43));
    }
    @Test
    void testGetTrainDepartureBasedOnIDReturnsNullWhenWrongId() {
        trainDispatchSystem.addTrainDeparture(42, "Trondheim", "12:00", "Oslo");
        assertNull(trainDispatchSystem.getTrainDepartureBasedOnID(43));
    }
    // Tests that the getTrainDeparturesBasedOnDestination method returns a list of all the trains with the given destination.
    @Test
    void testGetTrainDeparturesBasedOnDestination() {
        trainDispatchSystem.addTrainDeparture(42, "Trondheim", "12:00", "Oslo");
        trainDispatchSystem.addTrainDeparture(43, "Trondheim", "13:00", "Oslo");
        trainDispatchSystem.addTrainDeparture(44, "Trondheim", "11:00", "Oslo");
        List<TrainDeparture> departuresForDestination = trainDispatchSystem.getTrainDeparturesBasedOnDestination("Oslo");
        assertEquals(3, departuresForDestination.size());
    }
    @Test
    void testGetTrainDeparturesBasedOnDestinationReturnsEmptyList() {
        List<TrainDeparture> departuresForDestination = trainDispatchSystem.getTrainDeparturesBasedOnDestination("Oslo");
        assertEquals(0, departuresForDestination.size());
    }
    @Test
    void testGetTrainDeparturesBasedOnDestinationReturnsEmptyListWhenWrongDestination() {
        trainDispatchSystem.addTrainDeparture(42, "Trondheim", "12:00", "Oslo");
        trainDispatchSystem.addTrainDeparture(43, "Trondheim", "13:00", "Oslo");
        trainDispatchSystem.addTrainDeparture(44, "Trondheim", "11:00", "Oslo");
        List<TrainDeparture> departuresForDestination = trainDispatchSystem.getTrainDeparturesBasedOnDestination("Bergen");
        assertEquals(0, departuresForDestination.size());
    }
    @Test
    void testGetTrainDeparturesBasedOnDestinationReturnsEmptyListWhenEmpty() {
        List<TrainDeparture> departuresForDestination = trainDispatchSystem.getTrainDeparturesBasedOnDestination("Oslo");
        assertEquals(0, departuresForDestination.size());
    }
    @Test
    void testGetTrainDeparturesBasedOnDestinationReturnsEmptyListWhenWrongDestinationAndEmpty() {
        List<TrainDeparture> departuresForDestination = trainDispatchSystem.getTrainDeparturesBasedOnDestination("Bergen");
        assertEquals(0, departuresForDestination.size());
    }
    @Test
    void testGetTrainDeparturesBasedOnDestinationReturnsEmptyListWhenWrongDestinationAndNotEmpty() {
        trainDispatchSystem.addTrainDeparture(42, "Trondheim", "12:00", "Oslo");
        trainDispatchSystem.addTrainDeparture(43, "Trondheim", "13:00", "Oslo");
        trainDispatchSystem.addTrainDeparture(44, "Trondheim", "11:00", "Oslo");
        List<TrainDeparture> departuresForDestination = trainDispatchSystem.getTrainDeparturesBasedOnDestination("Bergen");
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
    void testAssignTrainToTrack() {
        trainDispatchSystem.addTrainDeparture(42, "Trondheim", "12:00", "Oslo");
        trainDispatchSystem.assignTrainToTrack(42, 1);
        TrainDeparture trainDeparture = trainDispatchSystem.getTrainDepartureBasedOnID(42);
        assertEquals(1, trainDeparture.getTrackNumber());
    }

    @Test
    void testAddDelayToTrain() {
        trainDispatchSystem.addTrainDeparture(42, "Trondheim", "12:00", "Oslo");
        trainDispatchSystem.addDelayToTrain(42, "01:00");
        TrainDeparture trainDeparture = trainDispatchSystem.getTrainDepartureBasedOnID(42);
        assertEquals("01:00", trainDeparture.getDelay().toString());
    }

    @Test // Tests that the listAllTrains method returns a list of all the trains in the system, sorted by departure time.
    void testListAllTrains() {
        trainDispatchSystem.addTrainDeparture(42, "Trondheim", "12:00", "Oslo");
        trainDispatchSystem.addTrainDeparture(43, "Trondheim", "13:00", "Oslo");
        trainDispatchSystem.addTrainDeparture(44, "Trondheim", "11:00", "Oslo");
        List<TrainDeparture> trainDepartures = trainDispatchSystem.listAllTrains();
        assertEquals(44, trainDepartures.get(0).getTrainId());
        assertEquals(42, trainDepartures.get(1).getTrainId());
        assertEquals(43, trainDepartures.get(2).getTrainId());
    }



}