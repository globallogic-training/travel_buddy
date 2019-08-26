
package com.challenge.travel_buddy.flight.services.model.Flight;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("going")
    @Expose
    private Going going;
    @SerializedName("returning")
    @Expose
    private Object returning;
    @SerializedName("showOutlook")
    @Expose
    private Boolean showOutlook;

    public Going getGoing() {
        return going;
    }

    public void setGoing(Going going) {
        this.going = going;
    }

    public Object getReturning() {
        return returning;
    }

    public void setReturning(Object returning) {
        this.returning = returning;
    }

    public Boolean getShowOutlook() {
        return showOutlook;
    }

    public void setShowOutlook(Boolean showOutlook) {
        this.showOutlook = showOutlook;
    }

}
