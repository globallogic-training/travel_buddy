
package com.challenge.travel_buddy.flight.services.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Airport {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("n")
    @Expose
    private String n;
    @SerializedName("t")
    @Expose
    private String t;
    @SerializedName("mt")
    @Expose
    private String mt;
    @SerializedName("xtr")
    @Expose
    private Xtr xtr;
    @SerializedName("iata")
    @Expose
    private String iata;
    @SerializedName("ct")
    @Expose
    private Ct ct;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getMt() {
        return mt;
    }

    public void setMt(String mt) {
        this.mt = mt;
    }

    public Xtr getXtr() {
        return xtr;
    }

    public void setXtr(Xtr xtr) {
        this.xtr = xtr;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public Ct getCt() {
        return ct;
    }

    public void setCt(Ct ct) {
        this.ct = ct;
    }

}
