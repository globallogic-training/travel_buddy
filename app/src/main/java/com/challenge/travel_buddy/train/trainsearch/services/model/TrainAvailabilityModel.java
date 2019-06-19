package com.challenge.travel_buddy.train.trainsearch.services.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class TrainAvailabilityModel {
    @SerializedName("data")
    @Expose
    private Map<String, Map<String, Available_Status>> data;

    @SerializedName("metadata")
    @Expose
    private MetaData metadata;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public MetaData getMetadata() {
        return metadata;
    }

    public void setMetadata(MetaData metadata) {
        this.metadata = metadata;
    }

    public Map<String, Map<String, Available_Status>> getData() {
        return data;
    }

    public void setData(Map<String, Map<String, Available_Status>> data) {
        this.data = data;
    }
}
