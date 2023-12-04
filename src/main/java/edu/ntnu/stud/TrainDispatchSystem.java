package edu.ntnu.stud;

import java.util.*;

public class TrainDispatchSystem {
    HashMap<String, TrainDeparture> trainDepartures = new HashMap<>();
    public TrainDispatchSystem() {
    }
    public void addTrainDeparture(TrainDeparture trainDeparture) throws IllegalArgumentException{
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
}
