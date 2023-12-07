package edu.ntnu.stud;

import java.util.*;

/**
 * This is the main class for the train dispatch application.
 */
public class TrainDispatchSystem { // TODO: Fill in the class definition
  static HashMap<String, TrainDeparture> trainDepartures = new HashMap<>();

  // The constructor creates a new HashMap that will contain the train departures.
  public TrainDispatchSystem() {
  }
  public void addTrainDeparture(TrainDeparture trainDeparture) throws IllegalArgumentException{
    // Throws an IllegalArgumentException if a train with the same id already exists.
    if (trainDepartures.containsKey(trainDeparture.getTrainId())) {
      throw new IllegalArgumentException("Train with id " + trainDeparture.getTrainId() + " already exists");
    } else {
      // Adds the train departure to the HashMap.
      trainDepartures.put(trainDeparture.getTrainId(), trainDeparture);
    }
  }
  public static TrainDeparture getTrainDepartureBasedOnID(String trainId) {
    if (trainId == null || trainId.isEmpty()) {
      throw new IllegalArgumentException("Train ID cannot be null or empty");
    }
    TrainDeparture trainDeparture = trainDepartures.get(trainId);
    if (trainDeparture == null) {
      System.out.println("No train departure found with the provided ID");
      return null;
    }
    return trainDeparture;
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
    departureList.sort(new Comparator<TrainDeparture>() {
      @Override
      public int compare(TrainDeparture o1, TrainDeparture o2) {
        return o1.getDepartureTime().compareTo(o2.getDepartureTime());
      }
    });
    return departureList;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<String, TrainDeparture> entry : trainDepartures.entrySet()) {
      sb.append(entry.getValue().toString());
      sb.append("\n");
    }
    return sb.toString();
  }
}
