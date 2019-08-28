
package com.challenge.travel_buddy.flight.services.model.Flight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Map;

public class Data {

    @SerializedName("providers")
    @Expose
    private ArrayList<Integer> providers;

    @SerializedName("results")
    @Expose
    private Map<String,Object> results;

    public ArrayList<Integer> getProviders() {
        return providers;
    }

    public void setProviders(ArrayList<Integer> providers) {
        this.providers = providers;
    }

    public Map<String, Object> getResults() {
        return results;
    }

    public void setResults(Map<String, Object> results) {
        this.results = results;
    }

}
