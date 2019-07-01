package com.challenge.travel_buddy.bus.services.model;

import com.google.gson.annotations.SerializedName;

public class ResHeaderModel {

    @SerializedName("status")
    public String status;

    @SerializedName("QTime")
    public String QTime;

    @SerializedName("params")
    public RespHeaderParamModel params;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQTime() {
        return QTime;
    }

    public void setQTime(String QTime) {
        this.QTime = QTime;
    }

    public RespHeaderParamModel getParams() {
        return params;
    }

    public void setParams(RespHeaderParamModel params) {
        this.params = params;
    }
}
