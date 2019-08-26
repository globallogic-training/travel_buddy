
package com.challenge.travel_buddy.flight.services.model.Flight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FlightData {

    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("errors")
    @Expose
    private Object errors;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }

}
