
package com.challenge.travel_buddy.flight.services.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("xid")
    @Expose
    private Integer xid;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("cityName")
    @Expose
    private String cityName;
    @SerializedName("stateName")
    @Expose
    private String stateName;
    @SerializedName("countryName")
    @Expose
    private String countryName;
    @SerializedName("continentName")
    @Expose
    private String continentName;
    @SerializedName("airportCode")
    @Expose
    private String airportCode;
    @SerializedName("allAirport")
    @Expose
    private Boolean allAirport;
    @SerializedName("airportName")
    @Expose
    private String airportName;
    @SerializedName("nearByAirport")
    @Expose
    private Boolean nearByAirport;
    @SerializedName("nearByCity")
    @Expose
    private NearByCity nearByCity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getXid() {
        return xid;
    }

    public void setXid(Integer xid) {
        this.xid = xid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public Boolean getAllAirport() {
        return allAirport;
    }

    public void setAllAirport(Boolean allAirport) {
        this.allAirport = allAirport;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public Boolean getNearByAirport() {
        return nearByAirport;
    }

    public void setNearByAirport(Boolean nearByAirport) {
        this.nearByAirport = nearByAirport;
    }

    public NearByCity getNearByCity() {
        return nearByCity;
    }

    public void setNearByCity(NearByCity nearByCity) {
        this.nearByCity = nearByCity;
    }

}
