package com.challenge.travel_buddy.train.services.model;

import com.google.gson.annotations.SerializedName;

public class SearchStationModel {
    @SerializedName("a")
    private String a;
    @SerializedName("xid")
    private String xid;
    @SerializedName("c")
    private String c;
    @SerializedName("e")
    private String e;
    @SerializedName("lon")
    private String lon;
    @SerializedName("lat")
    private String lat;


    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getXid() {
        return xid;
    }

    public void setXid(String xid) {
        this.xid = xid;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
