
package com.challenge.travel_buddy.flight.services.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Xtr {

    @SerializedName("eiata")
    @Expose
    private String eiata;
    @SerializedName("cn")
    @Expose
    private String cn;
    @SerializedName("cid")
    @Expose
    private String cid;
    @SerializedName("cc")
    @Expose
    private String cc;
    @SerializedName("dis_type")
    @Expose
    private String disType;
    @SerializedName("srname")
    @Expose
    private String srname;
    @SerializedName("srid")
    @Expose
    private String srid;
    @SerializedName("ar")
    @Expose
    private Integer ar;
    @SerializedName("eid")
    @Expose
    private String eid;
    @SerializedName("cnt_n")
    @Expose
    private String cntN;
    @SerializedName("cnt_id")
    @Expose
    private String cntId;
    @SerializedName("dis")
    @Expose
    private Double dis;
    @SerializedName("fsc")
    @Expose
    private Integer fsc;

    public String getEiata() {
        return eiata;
    }

    public void setEiata(String eiata) {
        this.eiata = eiata;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getDisType() {
        return disType;
    }

    public void setDisType(String disType) {
        this.disType = disType;
    }

    public String getSrname() {
        return srname;
    }

    public void setSrname(String srname) {
        this.srname = srname;
    }

    public String getSrid() {
        return srid;
    }

    public void setSrid(String srid) {
        this.srid = srid;
    }

    public Integer getAr() {
        return ar;
    }

    public void setAr(Integer ar) {
        this.ar = ar;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getCntN() {
        return cntN;
    }

    public void setCntN(String cntN) {
        this.cntN = cntN;
    }

    public String getCntId() {
        return cntId;
    }

    public void setCntId(String cntId) {
        this.cntId = cntId;
    }

    public Double getDis() {
        return dis;
    }

    public void setDis(Double dis) {
        this.dis = dis;
    }

    public Integer getFsc() {
        return fsc;
    }

    public void setFsc(Integer fsc) {
        this.fsc = fsc;
    }

}
