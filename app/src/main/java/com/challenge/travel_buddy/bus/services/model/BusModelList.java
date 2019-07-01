package com.challenge.travel_buddy.bus.services.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class BusModelList {
    @SerializedName("docs")
    public List<BusModel> docs;

    public List<BusModel> getDocs() {
        return docs;
    }

    public void setDocs(List<BusModel> docs) {
        this.docs = docs;
    }
}
