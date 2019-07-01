
package com.challenge.travel_buddy.bus.services.model.busresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BpDatum {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Vbpname")
    @Expose
    private String vbpname;
    @SerializedName("BpTm")
    @Expose
    private String bpTm;
    @SerializedName("bpTminmin")
    @Expose
    private Integer bpTminmin;
    @SerializedName("eta")
    @Expose
    private Object eta;
    @SerializedName("Address")
    @Expose
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVbpname() {
        return vbpname;
    }

    public void setVbpname(String vbpname) {
        this.vbpname = vbpname;
    }

    public String getBpTm() {
        return bpTm;
    }

    public void setBpTm(String bpTm) {
        this.bpTm = bpTm;
    }

    public Integer getBpTminmin() {
        return bpTminmin;
    }

    public void setBpTminmin(Integer bpTminmin) {
        this.bpTminmin = bpTminmin;
    }

    public Object getEta() {
        return eta;
    }

    public void setEta(Object eta) {
        this.eta = eta;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
