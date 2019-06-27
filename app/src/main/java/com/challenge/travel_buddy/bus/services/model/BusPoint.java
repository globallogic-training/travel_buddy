package com.challenge.travel_buddy.bus.services.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusPoint {

    @SerializedName("ID")
    @Expose
    private Integer iD;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("cc")
    @Expose
    private String cc;
    @SerializedName("rank")
    @Expose
    private Integer rank;
    @SerializedName("id")
    @Expose
    private Object id;
    @SerializedName("parentLocation")
    @Expose
    private Integer parentLocation;
    @SerializedName("locationType")
    @Expose
    private String locationType;

    public Integer getID() {
        return iD;
    }

    public void setID(Integer iD) {
        this.iD = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public Integer getParentLocation() {
        return parentLocation;
    }

    public void setParentLocation(Integer parentLocation) {
        this.parentLocation = parentLocation;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

}