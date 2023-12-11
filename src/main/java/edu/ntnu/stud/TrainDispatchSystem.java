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
  private LocalTime currentTime = LocalTime.of(0, 1); // Default to 00:01

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
    List<TrainDeparture> departureList = departureValues.stream()
        .filter(departure -> !departure.getDepartureTime().plusHours(departure.getDelay().getHour()).plusMinutes(departure.getDelay().getMinute()).isBefore(currentTime))
        .sorted(Comparator.comparing(TrainDeparture::getDepartureTime))
        .collect(Collectors.toList());
    return departureList;
  }

  public void setCurrentTime(LocalTime newTime) {
    if (newTime.isAfter(currentTime)) {
      this.currentTime = newTime;
    } else {
      throw new IllegalArgumentException("New time cannot be earlier than current time");
    }
  }

  public LocalTime getCurrentTime() {
    return this.currentTime;
  }

  public String formatTrainDeparture(TrainDeparture departure) {
    String lineSeparator = "+------+--------+--------------+-------------+-------------+-------+\n";
    StringBuilder table = new StringBuilder(lineSeparator);
    table.append(String.format("| %-4s | %-6s | %-12s | %-11s | %-5s | %-5s |\n",
        departure.getTrainId(),
        departure.getLine(),
        departure.getDepartureTime(),
        departure.getDestination(),
        departure.getDelay().equals(LocalTime.of(0, 0)) ? "" : departure.getDelay().toString(),
        departure.getTrackNumber() == -1 ? "" : Integer.toString(departure.getTrackNumber())
    ));
    table.append(lineSeparator);
    return table.toString();
  }
  public String formatTrainsTableFormat(List<TrainDeparture> departures) {
    StringBuilder table = new StringBuilder();

    // Display the current time
    table.append("Current time: ").append(currentTime).append("\n\n");

    String lineSeparator = "+--------------+--------+------+-------------+-------+-------+\n";

    // Table header
    table.append(lineSeparator);
    table.append("| Departure    | Line   | ID   | Destination | Delay | Track |\n");
    table.append(lineSeparator);

    // Table rows
    for (TrainDeparture departure : departures) {
      table.append(String.format("| %-12s | %-6s | %-4s | %-11s | %-5s | %-5s |\n",
          departure.getDepartureTime(),
          departure.getLine(),
          departure.getTrainId(),
          departure.getDestination(),
          departure.getDelay().equals(LocalTime.of(0, 0)) ? "" : departure.getDelay().toString(),
          departure.getTrackNumber() == -1 ? "" : Integer.toString(departure.getTrackNumber())));
      table.append(lineSeparator);
    }

    return table.toString();
  }
}