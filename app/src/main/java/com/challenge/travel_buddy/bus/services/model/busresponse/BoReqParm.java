
package com.challenge.travel_buddy.bus.services.model.busresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BoReqParm {

    @SerializedName("isBpDpSl")
    @Expose
    private Boolean isBpDpSl;
    @SerializedName("isBpDpTb")
    @Expose
    private Boolean isBpDpTb;
    @SerializedName("isAvailCatCard")
    @Expose
    private Boolean isAvailCatCard;
    @SerializedName("isAvailReschedule")
    @Expose
    private Boolean isAvailReschedule;
    @SerializedName("isSingleLadies")
    @Expose
    private Boolean isSingleLadies;
    @SerializedName("reschedulePolicy")
    @Expose
    private String reschedulePolicy;
    @SerializedName("isAvailSrCitizen")
    @Expose
    private Boolean isAvailSrCitizen;

    public Boolean getIsBpDpSl() {
        return isBpDpSl;
    }

    public void setIsBpDpSl(Boolean isBpDpSl) {
        this.isBpDpSl = isBpDpSl;
    }

    public Boolean getIsBpDpTb() {
        return isBpDpTb;
    }

    public void setIsBpDpTb(Boolean isBpDpTb) {
        this.isBpDpTb = isBpDpTb;
    }

    public Boolean getIsAvailCatCard() {
        return isAvailCatCard;
    }

    public void setIsAvailCatCard(Boolean isAvailCatCard) {
        this.isAvailCatCard = isAvailCatCard;
    }

    public Boolean getIsAvailReschedule() {
        return isAvailReschedule;
    }

    public void setIsAvailReschedule(Boolean isAvailReschedule) {
        this.isAvailReschedule = isAvailReschedule;
    }

    public Boolean getIsSingleLadies() {
        return isSingleLadies;
    }

    public void setIsSingleLadies(Boolean isSingleLadies) {
        this.isSingleLadies = isSingleLadies;
    }

    public String getReschedulePolicy() {
        return reschedulePolicy;
    }

    public void setReschedulePolicy(String reschedulePolicy) {
        this.reschedulePolicy = reschedulePolicy;
    }

    public Boolean getIsAvailSrCitizen() {
        return isAvailSrCitizen;
    }

    public void setIsAvailSrCitizen(Boolean isAvailSrCitizen) {
        this.isAvailSrCitizen = isAvailSrCitizen;
    }

}
