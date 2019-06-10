package com.challenge.travel_buddy.train.trainsearch.services.model;

import com.google.gson.annotations.SerializedName;

public class Train {

    @SerializedName("data")
    TrainData data;
//    List<Map <String, Map<String, Available_Status>>> seats;


    public TrainData getData() {
        return data;
    }

    public void setData(TrainData data) {
        this.data = data;
    }

//    public List<Map<String, Map<String, Available_Status>>> getSeats() {
//        return seats;
//    }
//
//    public void setSeats(List<Map<String, Map<String, Available_Status>>> seats) {
//        this.seats = seats;
//    }
}
