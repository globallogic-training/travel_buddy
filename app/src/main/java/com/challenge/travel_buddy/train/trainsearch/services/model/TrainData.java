package com.challenge.travel_buddy.train.trainsearch.services.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TrainData {

    @SerializedName("trainsBetweenStations")
    ArrayList<TrainDataModel> trainsBetweenStations;

    public ArrayList<TrainDataModel> getTrainsBetweenStations() {
        return trainsBetweenStations;
    }

    public void setTrainsBetweenStations(ArrayList<TrainDataModel> trainsBetweenStations) {
        this.trainsBetweenStations = trainsBetweenStations;
    }
}
