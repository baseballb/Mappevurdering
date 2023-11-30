package edu.ntnu.stud;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrainDepartureTest {
    @Test
    void testTrainDeparture() {
        TrainDeparture trainDeparture = new TrainDeparture("42", "Trondheim", "12:00", "Oslo");
        assertNotNull(trainDeparture);
    }
    @Test
    void testGetTrainId() {
        TrainDeparture trainDeparture = new TrainDeparture("42", "Trondheim", "12:00", "Oslo");
        assertEquals("42", trainDeparture.getTrainId());
    }
    @Test
    void testGetLine() {
        TrainDeparture trainDeparture = new TrainDeparture("42", "Trondheim", "12:00", "Oslo");
        assertEquals("Trondheim", trainDeparture.getLine());
    }
    @Test
    void testGetDepartureTime() {
        TrainDeparture trainDeparture = new TrainDeparture("42", "Trondheim", "12:00", "Oslo");
        assertEquals("12:00", trainDeparture.getDepartureTime().toString());
    }
    @Test
    void testGetDestination() {
        TrainDeparture trainDeparture = new TrainDeparture("42", "Trondheim", "12:00", "Oslo");
        assertEquals("Oslo", trainDeparture.getDestination());
    }
    @Test
    void testGetTrackNumber() {
        TrainDeparture trainDeparture = new TrainDeparture("42", "Trondheim", "12:00", "Oslo");
        assertEquals(-1, trainDeparture.getTrackNumber());
    }
    @Test
    void testGetDelay() {
        TrainDeparture trainDeparture = new TrainDeparture("42", "Trondheim", "12:00", "Oslo");
        assertEquals("00:00", trainDeparture.getDelay().toString());
    }
    @Test
    void testSetTrackNumber() {
        TrainDeparture trainDeparture = new TrainDeparture("42", "Trondheim", "12:00", "Oslo");
        trainDeparture.setTrackNumber(1);
        assertEquals(1, trainDeparture.getTrackNumber());
    }
    @Test
    void testSetDelay() {
        TrainDeparture trainDeparture = new TrainDeparture("42", "Trondheim", "12:00", "Oslo");
        trainDeparture.setDelay("01:00");
        assertEquals("01:00", trainDeparture.getDelay().toString());
    }

}