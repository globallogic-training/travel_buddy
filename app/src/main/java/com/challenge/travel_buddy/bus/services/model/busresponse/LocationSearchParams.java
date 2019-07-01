
package com.challenge.travel_buddy.bus.services.model.busresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LocationSearchParams {

    @SerializedName("isExactMatch")
    @Expose
    private Boolean isExactMatch;
    @SerializedName("sourceBp")
    @Expose
    private String sourceBp;
    @SerializedName("destinationDp")
    @Expose
    private String destinationDp;
    @SerializedName("bpId")
    @Expose
    private Integer bpId;
    @SerializedName("dpId")
    @Expose
    private Integer dpId;

    public Boolean getIsExactMatch() {
        return isExactMatch;
    }

    public void setIsExactMatch(Boolean isExactMatch) {
        this.isExactMatch = isExactMatch;
    }

    public String getSourceBp() {
        return sourceBp;
    }

    public void setSourceBp(String sourceBp) {
        this.sourceBp = sourceBp;
    }

    public String getDestinationDp() {
        return destinationDp;
    }

    public void setDestinationDp(String destinationDp) {
        this.destinationDp = destinationDp;
    }

    public Integer getBpId() {
        return bpId;
    }

    public void setBpId(Integer bpId) {
        this.bpId = bpId;
    }

    public Integer getDpId() {
        return dpId;
    }

    public void setDpId(Integer dpId) {
        this.dpId = dpId;
    }

}
