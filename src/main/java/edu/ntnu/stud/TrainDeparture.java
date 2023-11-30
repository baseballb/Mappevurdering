package edu.ntnu.stud;

import java.time.LocalTime;

public class TrainDeparture {
  private final String trainId;
  /* trainId is final because it should not be possible to change the id of a train
    after it has been created.*/
  private final String line;
  /* line is final because it should not be possible to change the line of a train
    after it has been created.*/
  private final LocalTime departureTime;
  /* departureTime is final because it should not be possible to change the departure time of a train
    after it has been created. Rather, the delay should be added to the departure time.*/
  private final String destination;
  /* destination is final because it should not be possible to change the destination of a train
    after it has been created.*/
  private int trackNumber = -1;
  /* trackNumber is not final because it should be possible to change the track number of a train
    if the train is assigned to a different track.*/
  private LocalTime delay = LocalTime.of(0, 0);
  public TrainDeparture(String trainId, String line, String departureTime, String destination) {
    this.trainId = trainId;
    this.line = line;
    this.departureTime = LocalTime.parse(departureTime);
    this.destination = destination;
  }
  public void setTrackNumber(int trackNumber) {
    this.trackNumber = trackNumber;
  }
public void setDelay(String delay) {
    this.delay = LocalTime.parse(delay);
  }
  public String getTrainId() {
    return trainId;
  }
  public String getLine() {
    return line;
  }
  public LocalTime getDepartureTime() {
    return departureTime;
  }
  public String getDestination() {
    return destination;
  }
  public int getTrackNumber() {
    return trackNumber;
  }
  public LocalTime getDelay() {
    return delay;
  }

  @Override
  public String toString() {
    return "TrainDeparture{" +
      "trainId='" + trainId + '\'' +
      ", line='" + line + '\'' +
      ", departureTime=" + departureTime +
      ", destination='" + destination + '\'' +
      ", trackNumber=" + trackNumber +
      ", delay=" + delay +
      '}';
  }
}
