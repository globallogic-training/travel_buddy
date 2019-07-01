package com.challenge.travel_buddy.bus.services.model;

import com.google.gson.annotations.SerializedName;

public class RespHeaderParamModel {
    @SerializedName("q")
    public String q;

    @SerializedName("df")
    public String df;

    @SerializedName("fl")
    public String fl;

    @SerializedName("json")
    public String json;

    @SerializedName("sort")
    public String sort;

    @SerializedName("fq")
    public String fq;

    @SerializedName("wt")
    public String wt;

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getDf() {
        return df;
    }

    public void setDf(String df) {
        this.df = df;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getFq() {
        return fq;
    }

    public void setFq(String fq) {
        this.fq = fq;
    }

    public String getWt() {
        return wt;
    }

    public void setWt(String wt) {
        this.wt = wt;
    }
}
