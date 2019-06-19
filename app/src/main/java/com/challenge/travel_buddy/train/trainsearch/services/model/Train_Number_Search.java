package com.challenge.travel_buddy.train.trainsearch.services.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Train_Number_Search {

    @SerializedName("p")
    @Expose
    private String p;
    @SerializedName("ct")
    @Expose
    private String ct;
    @SerializedName("c")
    @Expose
    private String c;
    @SerializedName("s")
    @Expose
    private String s;
    @SerializedName("d")
    @Expose
    private String d;
    @SerializedName("origin")
    @Expose
    private String origin;
    @SerializedName("destinationName")
    @Expose
    private String destinationName;
    @SerializedName("destination")
    @Expose
    private String destination;
    @SerializedName("cn")
    @Expose
    private String cn;
    @SerializedName("n")
    @Expose
    private String n;
    @SerializedName("originName")
    @Expose
    private String originName;

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

}
