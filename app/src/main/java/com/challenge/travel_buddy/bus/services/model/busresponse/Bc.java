
package com.challenge.travel_buddy.bus.services.model.busresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bc {

    @SerializedName("IsAc")
    @Expose
    private Boolean isAc;
    @SerializedName("IsNonAc")
    @Expose
    private Boolean isNonAc;
    @SerializedName("IsSeater")
    @Expose
    private Boolean isSeater;
    @SerializedName("IsSleeper")
    @Expose
    private Boolean isSleeper;

    public Boolean getIsAc() {
        return isAc;
    }

    public void setIsAc(Boolean isAc) {
        this.isAc = isAc;
    }

    public Boolean getIsNonAc() {
        return isNonAc;
    }

    public void setIsNonAc(Boolean isNonAc) {
        this.isNonAc = isNonAc;
    }

    public Boolean getIsSeater() {
        return isSeater;
    }

    public void setIsSeater(Boolean isSeater) {
        this.isSeater = isSeater;
    }

    public Boolean getIsSleeper() {
        return isSleeper;
    }

    public void setIsSleeper(Boolean isSleeper) {
        this.isSleeper = isSleeper;
    }

}
