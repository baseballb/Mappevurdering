package edu.ntnu.stud;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class TrainDispatchSystem {
  private HashMap<String, TrainDeparture> trainDepartures = new HashMap<>();

  public void addTrainDeparture(String trainId, String line, String departureTime, String destination) throws IllegalArgumentException {
    // TODO: Add error handling for wrong inputs (wrong time format, etc.)
    if (trainDepartures.containsKey(trainId)) {
      throw new IllegalArgumentException("Train with id " + trainId + " already exists");
    } else {
      TrainDeparture newTrainDeparture = new TrainDeparture(trainId, line, departureTime, destination);
      trainDepartures.put(trainId, newTrainDeparture);
    }
  }

  public TrainDeparture getTrainDepartureBasedOnID(String trainId) {
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

  public void assignTrainToTrack(String trainId, int trackNumber) {
    TrainDeparture trainDeparture = trainDepartures.get(trainId);
    trainDeparture.setTrackNumber(trackNumber);
  }

  public void addDelayToTrain(String trainId, String delay) {
    TrainDeparture trainDeparture = trainDepartures.get(trainId);
    trainDeparture.setDelay(delay);
  }

  public List<TrainDeparture> listAllTrains() {
    Collection<TrainDeparture> departureValues = trainDepartures.values();
    List<TrainDeparture> departureList = new ArrayList<>(departureValues);
    departureList.sort(Comparator.comparing(TrainDeparture::getDepartureTime));
    return departureList;
  }
}