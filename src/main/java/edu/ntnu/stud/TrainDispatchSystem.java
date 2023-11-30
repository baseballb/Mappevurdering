package edu.ntnu.stud;

import java.util.HashMap;

public class TrainDispatchSystem {
    HashMap<String, TrainDeparture> trainDepartures = new HashMap<>();
    public TrainDispatchSystem() {
    }
    public void addTrainDeparture(TrainDeparture trainDeparture) {
        if (trainDepartures.containsKey(trainDeparture.getTrainId())) {
            throw new IllegalArgumentException("Train with id " + trainDeparture.getTrainId() + " already exists");
        } else {
            trainDepartures.put(trainDeparture.getTrainId(), trainDeparture);
        }
    }
    public TrainDeparture getTrainDeparture(String trainId) {
        return trainDepartures.get(trainId);
    }
    public void assignTrainToTrack(String trainId, int trackNumber) {
        TrainDeparture trainDeparture = trainDepartures.get(trainId);
        trainDeparture.setTrackNumber(trackNumber);
    }
}
