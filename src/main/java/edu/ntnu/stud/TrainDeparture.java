package edu.ntnu.stud;

import java.time.LocalTime;

/**
 * The TrainDeparture class represents a train departure.
 * It contains information about the train ID, line,
 * departure time, destination, track number, and delay.
 */
public class TrainDeparture {
  // The ID of the train
  private final int trainId;

  // The line of the train
  private final String line;

  // The departure time of the train
  private final LocalTime departureTime;

  // The destination of the train
  private final String destination;

  // The track number of the train
  private int trackNumber = -1;

  // The delay of the train
  private LocalTime delay = LocalTime.of(0, 0);

  /**
   * Constructor for the TrainDeparture class.
   *
   * @param trainId The ID of the train
   * @param line The line of the train
   * @param departureTime The departure time of the train
   * @param destination The destination of the train
   */
  public TrainDeparture(int trainId, String line, String departureTime, String destination) {
    this.trainId = trainId;
    this.line = line;
    this.departureTime = LocalTime.parse(departureTime);
    this.destination = destination;
  }

  /**
   * Sets the track number of the train.
   *
   * @param trackNumber The track number
   */
  public void setTrackNumber(int trackNumber) {
    this.trackNumber = trackNumber;
  }

  /**
   * Sets the delay of the train.
   *
   * @param delay The delay
   */
  public void setDelay(String delay) {
    this.delay = LocalTime.parse(delay);
  }

  /**
   * Returns the ID of the train.
   *
   * @return The train ID
   */
  public int getTrainId() {
    return trainId;
  }

  /**
   * Returns the line of the train.
   *
   * @return The line
   */
  public String getLine() {
    return line;
  }

  /**
   * Returns the departure time of the train.
   *
   * @return The departure time
   */
  public LocalTime getDepartureTime() {
    return departureTime;
  }

  /**
   * Returns the destination of the train.
   *
   * @return The destination
   */
  public String getDestination() {
    return destination;
  }

  /**
   * Returns the track number of the train.
   *
   * @return The track number
   */
  public int getTrackNumber() {
    return trackNumber;
  }

  /**
   * Returns the delay of the train.
   *
   * @return The delay
   */
  public LocalTime getDelay() {
    return delay;
  }
}