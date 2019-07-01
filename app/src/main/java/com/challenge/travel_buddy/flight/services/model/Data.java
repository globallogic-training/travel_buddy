
package com.challenge.travel_buddy.flight.services.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("r")
    @Expose
    private List<AirportModel> r = null;

    public List<AirportModel> getR() {
        return r;
    }

    public void setR(List<AirportModel> r) {
        this.r = r;
    }

}
