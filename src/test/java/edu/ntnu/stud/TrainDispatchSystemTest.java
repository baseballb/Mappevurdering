package edu.ntnu.stud;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrainDispatchSystemTest {
    @Test
    void testAddTrainDeparture() {
        TrainDispatchSystem trainDispatchSystem = new TrainDispatchSystem();
        TrainDeparture trainDeparture = new TrainDeparture("42", "Trondheim", "12:00", "Oslo");
        trainDispatchSystem.addTrainDeparture(trainDeparture);
        assertEquals(trainDeparture, trainDispatchSystem.getTrainDeparture("42"));
    }
    @Test
    void testGetTrainDeparture() {
        TrainDispatchSystem trainDispatchSystem = new TrainDispatchSystem();
        TrainDeparture trainDeparture = new TrainDeparture("42", "Trondheim", "12:00", "Oslo");
        trainDispatchSystem.addTrainDeparture(trainDeparture);
        assertEquals(trainDeparture, trainDispatchSystem.getTrainDeparture("42"));
    }
    @Test
    void testGetTrainDepartureReturnsNull() {
        TrainDispatchSystem trainDispatchSystem = new TrainDispatchSystem();
        TrainDeparture trainDeparture = new TrainDeparture("42", "Trondheim", "12:00", "Oslo");
        trainDispatchSystem.addTrainDeparture(trainDeparture);
        assertNull(trainDispatchSystem.getTrainDeparture("43"));
    }
    @Test
    void testGetTrainDepartureReturnsNullWhenEmpty() {
        TrainDispatchSystem trainDispatchSystem = new TrainDispatchSystem();
        assertNull(trainDispatchSystem.getTrainDeparture("43"));
    }
    @Test
    void testGetTrainDepartureReturnsNullWhenWrongId() {
        TrainDispatchSystem trainDispatchSystem = new TrainDispatchSystem();
        TrainDeparture trainDeparture = new TrainDeparture("42", "Trondheim", "12:00", "Oslo");
        trainDispatchSystem.addTrainDeparture(trainDeparture);
        assertNull(trainDispatchSystem.getTrainDeparture("43"));
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

}