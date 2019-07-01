package com.challenge.travel_buddy.bus.services.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BusStationResp {

    @SerializedName("numFound")
    public int numFound;

    @SerializedName("start")
    public int start;

    @SerializedName("docs")
    public List<BusModel> docs;

    public List<BusModel> getDocs() {
        return docs;
    }

    public void setDocs(List<BusModel> docs) {
        this.docs = docs;
    }

    public int getNumFound() {
        return numFound;
    }

    public void setNumFound(int numFound) {
        this.numFound = numFound;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

}
