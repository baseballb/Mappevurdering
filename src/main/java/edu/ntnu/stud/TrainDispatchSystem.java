package edu.ntnu.stud;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents a train dispatch system.
 * It manages a collection of train departures and provides methods for adding departures,
 * assigning tracks to trains, adding delays to trains, and listing all trains.
 */
public class TrainDispatchSystem {
  private HashMap<Integer, TrainDeparture> trainDepartures = new HashMap<>();
  private LocalTime currentTime = LocalTime.of(0, 1); // Default to 00:01

  /**
   * Adds a new train departure to the system.
   *
   * @param trainId The ID of the train.
   * @param line The line of the train.
   * @param departureTime The departure time of the train.
   * @param destination The destination of the train.
   * @throws IllegalArgumentException If a train with the given ID already exists.
   */
  public void addTrainDeparture(int trainId, String line,
                                String departureTime, String destination)
      throws IllegalArgumentException {
    // TODO: Add error handling for wrong inputs (wrong time format, etc.)
    if (trainDepartures.containsKey(trainId)) {
      throw new IllegalArgumentException("Train with id " + trainId + " already exists");
    } else {
      TrainDeparture newTrainDeparture =
          new TrainDeparture(trainId, line, departureTime, destination);
      trainDepartures.put(trainId, newTrainDeparture);
    }
  }

  /**
   * Retrieves a train departure based on its ID.
   *
   * @param trainId The ID of the train.
   *
   * @return The TrainDeparture object with the given ID, or null if no such train exists.
   */
  public TrainDeparture getTrainDepartureBasedOnId(int trainId) {
    return trainDepartures.get(trainId);
  }

  /**
   * Retrieves all train departures with a given destination.
   *
   * @param destination The destination to search for.
   *
   * @return A list of TrainDeparture objects with the given destination.
   */
  public List<TrainDeparture> getTrainDeparturesBasedOnDestination(String destination) {
    List<TrainDeparture> departuresForDestination = new ArrayList<>();
    for (TrainDeparture departure : trainDepartures.values()) {
      if (departure.getDestination().equals(destination)) {
        departuresForDestination.add(departure);
      }
    }
    return departuresForDestination;
  }

  /**
   * Assigns a track to a train.
   *
   * @param trainId The ID of the train.
   * @param trackNumber The track number to assign.
   */
  public void assignTrainToTrack(int trainId, int trackNumber) {
    TrainDeparture trainDeparture = trainDepartures.get(trainId);
    trainDeparture.setTrackNumber(trackNumber);
  }

  /**
   * Adds a delay to a train.
   *
   * @param trainId The ID of the train.
   * @param delay The delay to add.
   */
  public void addDelayToTrain(int trainId, String delay) {
    TrainDeparture trainDeparture = trainDepartures.get(trainId);
    trainDeparture.setDelay(delay);
  }

  /**
   * This method lists all trains that have not yet departed.
   * It filters the train departures based on their departure time plus delay,
   * and only includes those that are not before the current time.
   * The resulting list is sorted by departure time.
   *
   * @return A list of TrainDeparture objects that have not yet departed, sorted by departure time.
   */
  public List<TrainDeparture> listAllTrains() {
    // Get all train departures
    Collection<TrainDeparture> departureValues = trainDepartures.values();

    // Filter the departures to include only those that have not yet departed
    // This is done by adding the delay to the departure time
    // and checking if it's before the current time
    // The filter operation returns a new stream that includes only
    // the departures that match the given predicate
    List<TrainDeparture> departureList = departureValues.stream()
        .filter(departure -> {
          LocalTime departureTimeWithDelay = departure.getDepartureTime()
              .plusHours(departure.getDelay().getHour())
              .plusMinutes(departure.getDelay().getMinute());
          return !departureTimeWithDelay.isBefore(currentTime);
        })

        // Sort the departures by departure time
        // The sorted operation returns a new stream that contains the departures in sorted order
        .sorted(Comparator.comparing(TrainDeparture::getDepartureTime))

        // Collect the departures into a list
        // The collect operation performs a mutable
        // reduction operation on the elements of the stream
        .collect(Collectors.toList());

    return departureList;
  }

  /**
   * Sets the current time.
   *
   * @param newTime The new current time.
   *
   * @throws IllegalArgumentException If the new time is earlier than the current time.
   */
  public void setCurrentTime(LocalTime newTime) {
    if (newTime.isAfter(currentTime)) {
      this.currentTime = newTime;
    } else {
      throw new IllegalArgumentException("New time cannot be earlier than current time");
    }
  }

  /**
   * Retrieves the current time.
   *
   * @return The current time.
   */
  public LocalTime getCurrentTime() {
    return this.currentTime;
  }
  /**
   * This method formats a single TrainDeparture object into a string representation of a table row.
   * The table row includes the departure time, line, ID, destination,
   * delay, and track number of the train departure.
   * If the delay is zero or the track number is -1, the corresponding cell is left empty.
   *
   * @param departure The TrainDeparture object to be formatted.
   * @return A string representing the TrainDeparture object as a table row.
   */

  public String formatTrainDeparture(TrainDeparture departure) {
    String lineSeparator = "+--------------+--------+------+-------------+-------+-------+\n";
    StringBuilder table = new StringBuilder(lineSeparator);

    // Display the current time
    table.append("Current time: ").append(currentTime).append("\n\n");

    // Table header
    table.append("| Departure    | Line   | ID   | Destination | Delay | Track |\n");
    table.append(lineSeparator);

    // Table row
    table.append(String.format("| %-12s | %-6s | %-4s | %-11s | %-5s | %-5s |\n",
        departure.getDepartureTime(),
        departure.getLine(),
        departure.getTrainId(),
        departure.getDestination(),
        departure.getDelay().equals(LocalTime.of(0, 0)) ? "" : departure.getDelay().toString(),
        departure.getTrackNumber() == -1 ? "" : Integer.toString(departure.getTrackNumber())));
    table.append(lineSeparator);

    return table.toString();
  }

  /**
   * This method formats a list of TrainDeparture objects into a string representation of a table.
   * The table includes a row for each train departure in the list, and each row
   * includes the departure time, line, ID, destination, delay,
   * and track number of the train departure.
   * If the delay is zero or the track number is -1, the corresponding cell is left empty.
   *
   * @param departures The list of TrainDeparture objects to be formatted.
   * @return A string representing the list of TrainDeparture objects as a table.
   */
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