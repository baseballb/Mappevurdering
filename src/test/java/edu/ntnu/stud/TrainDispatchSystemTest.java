package edu.ntnu.stud;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrainDispatchSystemTest {
    @Test
    void testAddTrainDeparture() {
        TrainDispatchSystem trainDispatchSystem = new TrainDispatchSystem();
        TrainDeparture trainDeparture = new TrainDeparture("42", "Trondheim", "12:00", "Oslo");
        trainDispatchSystem.addTrainDeparture(trainDeparture);
        assertEquals(trainDeparture, trainDispatchSystem.getTrainDepartureBasedOnID("42"));
    }
    @Test
    void testGetTrainDepartureBasedOnID() {
        TrainDispatchSystem trainDispatchSystem = new TrainDispatchSystem();
        TrainDeparture trainDeparture = new TrainDeparture("42", "Trondheim", "12:00", "Oslo");
        trainDispatchSystem.addTrainDeparture(trainDeparture);
        assertEquals(trainDeparture, trainDispatchSystem.getTrainDepartureBasedOnID("42"));
    }

    // Tests that the getTrainDeparturesBasedOnDestination method returns a list of all the trains with the given destination.
    @Test
    void testGetTrainDeparturesBasedOnDestination() {
        TrainDispatchSystem trainDispatchSystem = new TrainDispatchSystem();
        TrainDeparture trainDeparture1 = new TrainDeparture("42", "Trondheim", "12:00", "Oslo");
        TrainDeparture trainDeparture2 = new TrainDeparture("43", "Trondheim", "13:00", "Oslo");
        TrainDeparture trainDeparture3 = new TrainDeparture("44", "Trondheim", "11:00", "Oslo");
        trainDispatchSystem.addTrainDeparture(trainDeparture1);
        trainDispatchSystem.addTrainDeparture(trainDeparture2);
        trainDispatchSystem.addTrainDeparture(trainDeparture3);
        List<TrainDeparture> departuresForDestination = trainDispatchSystem.getTrainDeparturesBasedOnDestination("Oslo");
        assertEquals(3, departuresForDestination.size());
        assertTrue(departuresForDestination.contains(trainDeparture1));
        assertTrue(departuresForDestination.contains(trainDeparture2));
        assertTrue(departuresForDestination.contains(trainDeparture3));
    }
    @Test
    void testGetTrainDepartureReturnsNull() {
        TrainDispatchSystem trainDispatchSystem = new TrainDispatchSystem();
        TrainDeparture trainDeparture = new TrainDeparture("42", "Trondheim", "12:00", "Oslo");
        trainDispatchSystem.addTrainDeparture(trainDeparture);
        assertNull(trainDispatchSystem.getTrainDepartureBasedOnID("43"));
    }
    @Test
    void testGetTrainDepartureReturnsNullWhenEmpty() {
        TrainDispatchSystem trainDispatchSystem = new TrainDispatchSystem();
        assertNull(trainDispatchSystem.getTrainDepartureBasedOnID("43"));
    }
    @Test
    void testGetTrainDepartureReturnsNullWhenWrongId() {
        TrainDispatchSystem trainDispatchSystem = new TrainDispatchSystem();
        TrainDeparture trainDeparture = new TrainDeparture("42", "Trondheim", "12:00", "Oslo");
        trainDispatchSystem.addTrainDeparture(trainDeparture);
        assertNull(trainDispatchSystem.getTrainDepartureBasedOnID("43"));
    }
    @Test
    void testTrainDispatchSystem() {
        TrainDispatchSystem trainDispatchSystem = new TrainDispatchSystem();
        assertNotNull(trainDispatchSystem);
    }
    @Test // Test that adding a train departure with the same id throws an exception
    void testAddTrainDepartureThrowsException() {
        TrainDispatchSystem trainDispatchSystem = new TrainDispatchSystem();
        TrainDeparture trainDeparture = new TrainDeparture("42", "Trondheim", "12:00", "Oslo");
        trainDispatchSystem.addTrainDeparture(trainDeparture);
        assertThrows(IllegalArgumentException.class, () -> trainDispatchSystem.addTrainDeparture(trainDeparture));
    }
    @Test
    void testAssignTrainToTrack() {
        TrainDispatchSystem trainDispatchSystem = new TrainDispatchSystem();
        TrainDeparture trainDeparture = new TrainDeparture("42", "Trondheim", "12:00", "Oslo");
        trainDispatchSystem.addTrainDeparture(trainDeparture);
        trainDispatchSystem.assignTrainToTrack("42", 1);
        assertEquals(1, trainDeparture.getTrackNumber());
    }
    @Test
    void testAddDelayToTrain() {
        TrainDispatchSystem trainDispatchSystem = new TrainDispatchSystem();
        TrainDeparture trainDeparture = new TrainDeparture("42", "Trondheim", "12:00", "Oslo");
        trainDispatchSystem.addTrainDeparture(trainDeparture);
        trainDispatchSystem.addDelayToTrain("42", "01:00");
        assertEquals("01:00", trainDeparture.getDelay().toString());
    }
    @Test // Tests that the listAllTrains method returns a list of all the trains in the system, sorted by departure time.
    void testListAllTrains() {
        TrainDispatchSystem trainDispatchSystem = new TrainDispatchSystem();
        TrainDeparture trainDeparture1 = new TrainDeparture("42", "Trondheim", "12:00", "Oslo");
        TrainDeparture trainDeparture2 = new TrainDeparture("43", "Trondheim", "13:00", "Oslo");
        TrainDeparture trainDeparture3 = new TrainDeparture("44", "Trondheim", "11:00", "Oslo");
        trainDispatchSystem.addTrainDeparture(trainDeparture1);
        trainDispatchSystem.addTrainDeparture(trainDeparture2);
        trainDispatchSystem.addTrainDeparture(trainDeparture3);
        List<TrainDeparture> trainDepartures = trainDispatchSystem.listAllTrains();
        assertEquals(trainDeparture3, trainDepartures.get(0));
        assertEquals(trainDeparture1, trainDepartures.get(1));
        assertEquals(trainDeparture2, trainDepartures.get(2));
    }



}