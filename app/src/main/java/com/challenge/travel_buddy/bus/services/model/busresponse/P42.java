
package com.challenge.travel_buddy.bus.services.model.busresponse;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class P42 {

    @SerializedName("restStopList")
    @Expose
    private List<Object> restStopList = null;
    @SerializedName("boReqParm")
    @Expose
    private List<BoReqParm> boReqParm = null;
    @SerializedName("rgb")
    @Expose
    private Integer rgb;
    @SerializedName("priceDescriptor")
    @Expose
    private PriceDescriptor priceDescriptor;
    @SerializedName("LocationSearchParams")
    @Expose
    private LocationSearchParams locationSearchParams;
    @SerializedName("busImageCount")
    @Expose
    private Integer busImageCount;
    @SerializedName("zcf")
    @Expose
    private String zcf;
    @SerializedName("sponsor")
    @Expose
    private String sponsor;
    @SerializedName("viaRoutes")
    @Expose
    private String viaRoutes;
    @SerializedName("viaRtDisplay")
    @Expose
    private Object viaRtDisplay;
    @SerializedName("reschedulePolicy")
    @Expose
    private Object reschedulePolicy;
    @SerializedName("isOfflineCancel")
    @Expose
    private String isOfflineCancel;
    @SerializedName("isPrimaryPaxCancellable")
    @Expose
    private Boolean isPrimaryPaxCancellable;
    @SerializedName("viaroutecount")
    @Expose
    private Integer viaroutecount;

    public List<Object> getRestStopList() {
        return restStopList;
    }

    public void setRestStopList(List<Object> restStopList) {
        this.restStopList = restStopList;
    }

    public List<BoReqParm> getBoReqParm() {
        return boReqParm;
    }

    public void setBoReqParm(List<BoReqParm> boReqParm) {
        this.boReqParm = boReqParm;
    }

    public Integer getRgb() {
        return rgb;
    }

    public void setRgb(Integer rgb) {
        this.rgb = rgb;
    }

    public PriceDescriptor getPriceDescriptor() {
        return priceDescriptor;
    }

    public void setPriceDescriptor(PriceDescriptor priceDescriptor) {
        this.priceDescriptor = priceDescriptor;
    }

    public LocationSearchParams getLocationSearchParams() {
        return locationSearchParams;
    }

    public void setLocationSearchParams(LocationSearchParams locationSearchParams) {
        this.locationSearchParams = locationSearchParams;
    }

    public Integer getBusImageCount() {
        return busImageCount;
    }

    public void setBusImageCount(Integer busImageCount) {
        this.busImageCount = busImageCount;
    }

    public String getZcf() {
        return zcf;
    }

    public void setZcf(String zcf) {
        this.zcf = zcf;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getViaRoutes() {
        return viaRoutes;
    }

    public void setViaRoutes(String viaRoutes) {
        this.viaRoutes = viaRoutes;
    }

    public Object getViaRtDisplay() {
        return viaRtDisplay;
    }

    public void setViaRtDisplay(Object viaRtDisplay) {
        this.viaRtDisplay = viaRtDisplay;
    }

    public Object getReschedulePolicy() {
        return reschedulePolicy;
    }

    public void setReschedulePolicy(Object reschedulePolicy) {
        this.reschedulePolicy = reschedulePolicy;
    }

    public String getIsOfflineCancel() {
        return isOfflineCancel;
    }

    public void setIsOfflineCancel(String isOfflineCancel) {
        this.isOfflineCancel = isOfflineCancel;
    }

    public Boolean getIsPrimaryPaxCancellable() {
        return isPrimaryPaxCancellable;
    }

    public void setIsPrimaryPaxCancellable(Boolean isPrimaryPaxCancellable) {
        this.isPrimaryPaxCancellable = isPrimaryPaxCancellable;
    }

    public Integer getViaroutecount() {
        return viaroutecount;
    }

    public void setViaroutecount(Integer viaroutecount) {
        this.viaroutecount = viaroutecount;
    }

}
