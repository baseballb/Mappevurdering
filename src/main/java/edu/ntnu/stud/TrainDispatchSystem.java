package edu.ntnu.stud;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.time.LocalTime;

public class TrainDispatchSystem {
  private HashMap<Integer, TrainDeparture> trainDepartures = new HashMap<>();
  private LocalTime currentTime = LocalTime.of(6, 0); // Default to 06:00

  public void addTrainDeparture(int trainId, String line, String departureTime, String destination) throws IllegalArgumentException {
    // TODO: Add error handling for wrong inputs (wrong time format, etc.)
    if (trainDepartures.containsKey(trainId)) {
      throw new IllegalArgumentException("Train with id " + trainId + " already exists");
    } else {
      TrainDeparture newTrainDeparture = new TrainDeparture(trainId, line, departureTime, destination);
      trainDepartures.put(trainId, newTrainDeparture);
    }
  }

  public TrainDeparture getTrainDepartureBasedOnID(int trainId) {
    // TODO: Add error handling
    return trainDepartures.get(trainId);
  }

  public List<TrainDeparture> getTrainDeparturesBasedOnDestination(String destination) {
    List<TrainDeparture> departuresForDestination = new ArrayList<>();
    for (TrainDeparture departure : trainDepartures.values()) {
      if (departure.getDestination().equals(destination)) {
        departuresForDestination.add(departure);
      }
    }
    return departuresForDestination;
  }

  public void assignTrainToTrack(int trainId, int trackNumber) {
    TrainDeparture trainDeparture = trainDepartures.get(trainId);
    trainDeparture.setTrackNumber(trackNumber);
  }

  public void addDelayToTrain(int trainId, String delay) {
    TrainDeparture trainDeparture = trainDepartures.get(trainId);
    trainDeparture.setDelay(delay);
  }

  public List<TrainDeparture> listAllTrains() {
    Collection<TrainDeparture> departureValues = trainDepartures.values();
    return departureValues.stream()
        .filter(departure -> !departure.getDepartureTime().isBefore(currentTime))
        .sorted(Comparator.comparing(TrainDeparture::getDepartureTime))
        .collect(Collectors.toList());
  }

  public void setCurrentTime(LocalTime newTime) {
    this.currentTime = newTime;
  }

  public LocalTime getCurrentTime() {
    return this.currentTime;
  }
}