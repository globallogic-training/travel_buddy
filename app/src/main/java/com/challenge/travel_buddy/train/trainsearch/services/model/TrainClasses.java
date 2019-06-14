package com.challenge.travel_buddy.train.trainsearch.services.model;

import java.util.Map;

public class TrainClasses {
    private Map<String, Available_Status> availClasses;

    public Map<String, Available_Status> getAvailClasses() {
        return availClasses;
    }

    public void setAvailClasses(Map<String, Available_Status> availClasses) {
        this.availClasses = availClasses;
    }
}
