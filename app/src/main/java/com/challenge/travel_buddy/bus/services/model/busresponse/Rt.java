
package com.challenge.travel_buddy.bus.services.model.busresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Rt {

    @SerializedName("totRt")
    @Expose
    private Double totRt;
    @SerializedName("ct")
    @Expose
    private String ct;

    public Double getTotRt() {
        return totRt;
    }

    public void setTotRt(Double totRt) {
        this.totRt = totRt;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

}
