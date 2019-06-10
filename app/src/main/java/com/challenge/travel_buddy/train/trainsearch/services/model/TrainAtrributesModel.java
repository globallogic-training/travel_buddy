package com.challenge.travel_buddy.train.trainsearch.services.model;

import java.util.ArrayList;

public class TrainAtrributesModel {
    private Number id;
    private String number;
    private String localName;
    private String type;
    private ArrayList<String> fareClasses;
    private String departTime;
    private String arrivalTime;
    private Number dayOfJourney;
    private String daysOfOperation;
    private String averageRating;
    private Boolean dynamicFareApplicable;
    private Boolean bookable;

    public TrainAtrributesModel(Number id, String number, String localName, String type,
                                ArrayList<String> fareClasses, String departTime, String arrivalTime,
                                Number dayOfJourney, String daysOfOperation, String averageRating,
                                Boolean dynamicFareApplicable, Boolean bookable) {
        this.id = id;
        this.number = number;
        this.localName = localName;
        this.type = type;
        this.fareClasses = fareClasses;
        this.departTime = departTime;
        this.arrivalTime = arrivalTime;
        this.dayOfJourney = dayOfJourney;
        this.daysOfOperation = daysOfOperation;
        this.averageRating = averageRating;
        this.dynamicFareApplicable = dynamicFareApplicable;
        this.bookable = bookable;
    }

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<String> getFareClasses() {
        return fareClasses;
    }

    public void setFareClasses(ArrayList<String> fareClasses) {
        this.fareClasses = fareClasses;
    }

    public String getDepartTime() {
        return departTime;
    }

    public void setDepartTime(String departTime) {
        this.departTime = departTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Number getDayOfJourney() {
        return dayOfJourney;
    }

    public void setDayOfJourney(Number dayOfJourney) {
        this.dayOfJourney = dayOfJourney;
    }

    public String getDaysOfOperation() {
        return daysOfOperation;
    }

    public void setDaysOfOperation(String daysOfOperation) {
        this.daysOfOperation = daysOfOperation;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    public Boolean getDynamicFareApplicable() {
        return dynamicFareApplicable;
    }

    public void setDynamicFareApplicable(Boolean dynamicFareApplicable) {
        this.dynamicFareApplicable = dynamicFareApplicable;
    }

    public Boolean getBookable() {
        return bookable;
    }

    public void setBookable(Boolean bookable) {
        this.bookable = bookable;
    }
}
