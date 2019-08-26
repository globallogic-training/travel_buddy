
package com.challenge.travel_buddy.flight.services.model.Flight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("airline")
    @Expose
    private String airline;
    @SerializedName("airlineCode")
    @Expose
    private String airlineCode;
    @SerializedName("flightNumber")
    @Expose
    private String flightNumber;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("found")
    @Expose
    private long found;
    @SerializedName("fare")
    @Expose
    private Double fare;
    @SerializedName("providerId")
    @Expose
    private String providerId;
    @SerializedName("searchId")
    @Expose
    private String searchId;

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getFound() {
        return found;
    }

    public void setFound(long found) {
        this.found = found;
    }

    public Double getFare() {
        return fare;
    }

    public void setFare(Double fare) {
        this.fare = fare;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getSearchId() {
        return searchId;
    }

    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

}
