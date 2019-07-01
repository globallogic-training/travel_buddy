
package com.challenge.travel_buddy.bus.services.model.busresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SectionData {

    @SerializedName("redDealsCount")
    @Expose
    private Integer redDealsCount;
    @SerializedName("otgCount")
    @Expose
    private Integer otgCount;
    @SerializedName("gpsCount")
    @Expose
    private Integer gpsCount;
    @SerializedName("cancelServiceCount")
    @Expose
    private Integer cancelServiceCount;
    @SerializedName("reschedulServiceCount")
    @Expose
    private Integer reschedulServiceCount;
    @SerializedName("mticketServiceCount")
    @Expose
    private Integer mticketServiceCount;
    @SerializedName("ratingServiceCount")
    @Expose
    private Integer ratingServiceCount;
    @SerializedName("isExactMatchSearch")
    @Expose
    private Boolean isExactMatchSearch;
    @SerializedName("sectionCount")
    @Expose
    private Integer sectionCount;
    @SerializedName("intlBusImgUrl")
    @Expose
    private String intlBusImgUrl;
    @SerializedName("groupOperatorId")
    @Expose
    private Object groupOperatorId;
    @SerializedName("isExactMatch")
    @Expose
    private Boolean isExactMatch;
    @SerializedName("isCountryBpDpRender")
    @Expose
    private Boolean isCountryBpDpRender;

    public Integer getRedDealsCount() {
        return redDealsCount;
    }

    public void setRedDealsCount(Integer redDealsCount) {
        this.redDealsCount = redDealsCount;
    }

    public Integer getOtgCount() {
        return otgCount;
    }

    public void setOtgCount(Integer otgCount) {
        this.otgCount = otgCount;
    }

    public Integer getGpsCount() {
        return gpsCount;
    }

    public void setGpsCount(Integer gpsCount) {
        this.gpsCount = gpsCount;
    }

    public Integer getCancelServiceCount() {
        return cancelServiceCount;
    }

    public void setCancelServiceCount(Integer cancelServiceCount) {
        this.cancelServiceCount = cancelServiceCount;
    }

    public Integer getReschedulServiceCount() {
        return reschedulServiceCount;
    }

    public void setReschedulServiceCount(Integer reschedulServiceCount) {
        this.reschedulServiceCount = reschedulServiceCount;
    }

    public Integer getMticketServiceCount() {
        return mticketServiceCount;
    }

    public void setMticketServiceCount(Integer mticketServiceCount) {
        this.mticketServiceCount = mticketServiceCount;
    }

    public Integer getRatingServiceCount() {
        return ratingServiceCount;
    }

    public void setRatingServiceCount(Integer ratingServiceCount) {
        this.ratingServiceCount = ratingServiceCount;
    }

    public Boolean getIsExactMatchSearch() {
        return isExactMatchSearch;
    }

    public void setIsExactMatchSearch(Boolean isExactMatchSearch) {
        this.isExactMatchSearch = isExactMatchSearch;
    }

    public Integer getSectionCount() {
        return sectionCount;
    }

    public void setSectionCount(Integer sectionCount) {
        this.sectionCount = sectionCount;
    }

    public String getIntlBusImgUrl() {
        return intlBusImgUrl;
    }

    public void setIntlBusImgUrl(String intlBusImgUrl) {
        this.intlBusImgUrl = intlBusImgUrl;
    }

    public Object getGroupOperatorId() {
        return groupOperatorId;
    }

    public void setGroupOperatorId(Object groupOperatorId) {
        this.groupOperatorId = groupOperatorId;
    }

    public Boolean getIsExactMatch() {
        return isExactMatch;
    }

    public void setIsExactMatch(Boolean isExactMatch) {
        this.isExactMatch = isExactMatch;
    }

    public Boolean getIsCountryBpDpRender() {
        return isCountryBpDpRender;
    }

    public void setIsCountryBpDpRender(Boolean isCountryBpDpRender) {
        this.isCountryBpDpRender = isCountryBpDpRender;
    }

}
