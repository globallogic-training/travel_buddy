
package com.challenge.travel_buddy.flight.services.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ct {

    @SerializedName("n")
    @Expose
    private String n;
    @SerializedName("_id")
    @Expose
    private String id;

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
