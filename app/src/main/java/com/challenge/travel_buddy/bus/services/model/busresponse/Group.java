
package com.challenge.travel_buddy.bus.services.model.busresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Group {

    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("oId")
    @Expose
    private Integer oId;
    @SerializedName("dn")
    @Expose
    private String dn;
    @SerializedName("minFr")
    @Expose
    private Integer minFr;
    @SerializedName("iconUrl")
    @Expose
    private String iconUrl;
    @SerializedName("eDt")
    @Expose
    private String eDt;
    @SerializedName("td")
    @Expose
    private Integer td;
    @SerializedName("eDtMin")
    @Expose
    private Integer eDtMin;
    @SerializedName("rtcBusTypeText")
    @Expose
    private String rtcBusTypeText;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getOId() {
        return oId;
    }

    public void setOId(Integer oId) {
        this.oId = oId;
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }

    public Integer getMinFr() {
        return minFr;
    }

    public void setMinFr(Integer minFr) {
        this.minFr = minFr;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getEDt() {
        return eDt;
    }

    public void setEDt(String eDt) {
        this.eDt = eDt;
    }

    public Integer getTd() {
        return td;
    }

    public void setTd(Integer td) {
        this.td = td;
    }

    public Integer getEDtMin() {
        return eDtMin;
    }

    public void setEDtMin(Integer eDtMin) {
        this.eDtMin = eDtMin;
    }

    public String getRtcBusTypeText() {
        return rtcBusTypeText;
    }

    public void setRtcBusTypeText(String rtcBusTypeText) {
        this.rtcBusTypeText = rtcBusTypeText;
    }

}
