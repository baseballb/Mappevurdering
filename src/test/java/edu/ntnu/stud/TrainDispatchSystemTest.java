package edu.ntnu.stud;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrainDispatchSystemTest {
    private TrainDispatchSystem trainDispatchSystem;

    @BeforeEach
    void setUp() {
        trainDispatchSystem = new TrainDispatchSystem();
    }

    @Test
    void addTrainDepartureAddsTrainSuccessfully() {
        trainDispatchSystem.addTrainDeparture(42, "Trondheim", "12:00", "Oslo");
        TrainDeparture trainDeparture = trainDispatchSystem.getTrainDepartureBasedOnId(42);
        assertEquals(42, trainDeparture.getTrainId());
        assertEquals("Trondheim", trainDeparture.getLine());
        assertEquals(LocalTime.parse("12:00"), trainDeparture.getDepartureTime());
        assertEquals("Oslo", trainDeparture.getDestination());
    }

    @Test
    void getTrainDepartureBasedOnIDReturnsCorrectTrain() {
        trainDispatchSystem.addTrainDeparture(42, "Trondheim", "12:00", "Oslo");
        TrainDeparture trainDeparture = trainDispatchSystem.getTrainDepartureBasedOnId(42);
        assertEquals(42, trainDeparture.getTrainId());
        assertEquals("Trondheim", trainDeparture.getLine());
        assertEquals(LocalTime.parse("12:00"), trainDeparture.getDepartureTime());
        assertEquals("Oslo", trainDeparture.getDestination());
    }

    @Test
    void getTrainDepartureBasedOnIDReturnsNullForNonexistentTrain() {
        assertNull(trainDispatchSystem.getTrainDepartureBasedOnId(43));
    }

    @Test
    void getTrainDeparturesBasedOnDestinationReturnsCorrectTrain() {
        trainDispatchSystem.addTrainDeparture(42, "L4", "12:00", "Oslo");
        trainDispatchSystem.addTrainDeparture(43, "L4", "13:00", "Oslo");
        trainDispatchSystem.addTrainDeparture(44, "L4", "11:00", "Oslo");
        List<TrainDeparture> departuresForDestination = trainDispatchSystem.getTrainDeparturesFromDestination("Oslo");
        assertEquals(3, departuresForDestination.size());
    }

    @Test
    void getTrainDeparturesBasedOnDestinationReturnsEmptyListNonexistentDestination() {
        List<TrainDeparture> departuresForDestination = trainDispatchSystem.getTrainDeparturesFromDestination("Oslo");
        assertEquals(0, departuresForDestination.size());
    }

    @Test
    void addTrainDepartureThrowsException() {
        trainDispatchSystem.addTrainDeparture(42, "Trondheim", "12:00", "Oslo");
        assertThrows(IllegalArgumentException.class, () -> trainDispatchSystem.addTrainDeparture(42, "Trondheim", "12:00", "Oslo"));
    }

    @Test
    void assignTrainToTrackAssignsTrackSuccessfully() {
        trainDispatchSystem.addTrainDeparture(42, "Trondheim", "12:00", "Oslo");
        trainDispatchSystem.assignTrainToTrack(42, 1);
        TrainDeparture trainDeparture = trainDispatchSystem.getTrainDepartureBasedOnId(42);
        assertEquals(1, trainDeparture.getTrackNumber());
    }

    @Test
    void addDelayToTrainAddsDelaySuccessfully() {
        trainDispatchSystem.addTrainDeparture(42, "Trondheim", "12:00", "Oslo");
        trainDispatchSystem.addDelayToTrain(42, "01:00");
        TrainDeparture trainDeparture = trainDispatchSystem.getTrainDepartureBasedOnId(42);
        assertEquals("01:00", trainDeparture.getDelay().toString());
    }

    @Test
    void listAllTrainsReturnsTrainsInCorrectOrder() {
        trainDispatchSystem.addTrainDeparture(42, "Trondheim", "12:00", "Oslo");
        trainDispatchSystem.addTrainDeparture(43, "Trondheim", "13:00", "Oslo");
        trainDispatchSystem.addTrainDeparture(44, "Trondheim", "11:00", "Oslo");
        List<TrainDeparture> trainDepartures = trainDispatchSystem.listAllTrains();
        assertEquals(44, trainDepartures.get(0).getTrainId());
        assertEquals(42, trainDepartures.get(1).getTrainId());
        assertEquals(43, trainDepartures.get(2).getTrainId());
    }

    @Test
    void setCurrentTimeThrowsExceptionWhenNewTimeIsEarlier() {
        assertThrows(IllegalArgumentException.class, () -> trainDispatchSystem.setCurrentTime(LocalTime.of(0, 0)));
    }

    @Test
    void setCurrentTimeUpdatesTimeSuccessfully() {
        trainDispatchSystem.setCurrentTime(LocalTime.of(12, 0));
        assertEquals(LocalTime.of(12, 0), trainDispatchSystem.getCurrentTime());
    }
}