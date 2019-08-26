
package com.challenge.travel_buddy.flight.services.model.Flight;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ranges {

    @SerializedName("R")
    @Expose
    private List<Integer> r = null;
    @SerializedName("G")
    @Expose
    private List<Integer> g = null;
    @SerializedName("Y")
    @Expose
    private List<Integer> y = null;

    public List<Integer> getR() {
        return r;
    }

    public void setR(List<Integer> r) {
        this.r = r;
    }

    public List<Integer> getG() {
        return g;
    }

    public void setG(List<Integer> g) {
        this.g = g;
    }

    public List<Integer> getY() {
        return y;
    }

    public void setY(List<Integer> y) {
        this.y = y;
    }

}
