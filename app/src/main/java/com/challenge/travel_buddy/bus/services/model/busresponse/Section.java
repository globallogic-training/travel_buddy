
package com.challenge.travel_buddy.bus.services.model.busresponse;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Section {

    @SerializedName("src")
    @Expose
    private String src;
    @SerializedName("dst")
    @Expose
    private String dst;
    @SerializedName("dn")
    @Expose
    private String dn;
    @SerializedName("groups")
    @Expose
    private List<Group> groups = null;
    @SerializedName("sectionId")
    @Expose
    private Integer sectionId;
    @SerializedName("totalCount")
    @Expose
    private Integer totalCount;
    @SerializedName("privateCount")
    @Expose
    private Integer privateCount;
    @SerializedName("RedDealsCount")
    @Expose
    private Integer redDealsCount;
    @SerializedName("OTGCount")
    @Expose
    private Integer oTGCount;
    @SerializedName("GPSCount")
    @Expose
    private Integer gPSCount;
    @SerializedName("CancellableServiceCount")
    @Expose
    private Integer cancellableServiceCount;
    @SerializedName("ReschedullableServiceCount")
    @Expose
    private Integer reschedullableServiceCount;
    @SerializedName("MTicketCount")
    @Expose
    private Integer mTicketCount;
    @SerializedName("RatingsCount")
    @Expose
    private Integer ratingsCount;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDst() {
        return dst;
    }

    public void setDst(String dst) {
        this.dst = dst;
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPrivateCount() {
        return privateCount;
    }

    public void setPrivateCount(Integer privateCount) {
        this.privateCount = privateCount;
    }

    public Integer getRedDealsCount() {
        return redDealsCount;
    }

    public void setRedDealsCount(Integer redDealsCount) {
        this.redDealsCount = redDealsCount;
    }

    public Integer getOTGCount() {
        return oTGCount;
    }

    public void setOTGCount(Integer oTGCount) {
        this.oTGCount = oTGCount;
    }

    public Integer getGPSCount() {
        return gPSCount;
    }

    public void setGPSCount(Integer gPSCount) {
        this.gPSCount = gPSCount;
    }

    public Integer getCancellableServiceCount() {
        return cancellableServiceCount;
    }

    public void setCancellableServiceCount(Integer cancellableServiceCount) {
        this.cancellableServiceCount = cancellableServiceCount;
    }

    public Integer getReschedullableServiceCount() {
        return reschedullableServiceCount;
    }

    public void setReschedullableServiceCount(Integer reschedullableServiceCount) {
        this.reschedullableServiceCount = reschedullableServiceCount;
    }

    public Integer getMTicketCount() {
        return mTicketCount;
    }

    public void setMTicketCount(Integer mTicketCount) {
        this.mTicketCount = mTicketCount;
    }

    public Integer getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(Integer ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

}
