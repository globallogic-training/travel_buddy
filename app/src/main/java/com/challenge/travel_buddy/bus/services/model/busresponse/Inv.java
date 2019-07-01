
package com.challenge.travel_buddy.bus.services.model.busresponse;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Inv {

    @SerializedName("sid")
    @Expose
    private Integer sid;
    @SerializedName("did")
    @Expose
    private Integer did;
    @SerializedName("bt")
    @Expose
    private String bt;
    @SerializedName("sn")
    @Expose
    private String sn;
    @SerializedName("cp")
    @Expose
    private String cp;
    @SerializedName("rt")
    @Expose
    private Rt rt;
    @SerializedName("OpRtg")
    @Expose
    private Object opRtg;
    @SerializedName("dt")
    @Expose
    private String dt;
    @SerializedName("dm")
    @Expose
    private Integer dm;
    @SerializedName("at")
    @Expose
    private String at;
    @SerializedName("am")
    @Expose
    private Integer am;
    @SerializedName("dur")
    @Expose
    private Integer dur;
    @SerializedName("issa")
    @Expose
    private Boolean issa;
    @SerializedName("iscs")
    @Expose
    private Boolean iscs;
    @SerializedName("islt")
    @Expose
    private Boolean islt;
    @SerializedName("tz")
    @Expose
    private String tz;
    @SerializedName("isbd")
    @Expose
    private Boolean isbd;
    @SerializedName("si")
    @Expose
    private String si;
    @SerializedName("bsi")
    @Expose
    private Integer bsi;
    @SerializedName("vt")
    @Expose
    private String vt;
    @SerializedName("frLst")
    @Expose
    private List<Integer> frLst = null;
    @SerializedName("minfr")
    @Expose
    private Integer minfr;
    @SerializedName("oid")
    @Expose
    private Integer oid;
    @SerializedName("rid")
    @Expose
    private Integer rid;
    @SerializedName("ismt")
    @Expose
    private Boolean ismt;
    @SerializedName("Tvs")
    @Expose
    private String tvs;
    @SerializedName("p42")
    @Expose
    private P42 p42;
    @SerializedName("spt")
    @Expose
    private Integer spt;
    @SerializedName("WnSt")
    @Expose
    private Integer wnSt;
    @SerializedName("sSt")
    @Expose
    private Integer sSt;
    @SerializedName("nsa")
    @Expose
    private Integer nsa;
    @SerializedName("bs")
    @Expose
    private Double bs;
    @SerializedName("cur")
    @Expose
    private String cur;
    @SerializedName("lp")
    @Expose
    private String lp;
    @SerializedName("igId")
    @Expose
    private Integer igId;
    @SerializedName("isPc")
    @Expose
    private Boolean isPc;
    @SerializedName("busTypeId")
    @Expose
    private Integer busTypeId;
    @SerializedName("cmpg")
    @Expose
    private Object cmpg;
    @SerializedName("firstBpTime")
    @Expose
    private String firstBpTime;
    @SerializedName("isso")
    @Expose
    private Boolean isso;
    @SerializedName("isRescheduled")
    @Expose
    private Boolean isRescheduled;
    @SerializedName("rescheduleCharge")
    @Expose
    private Object rescheduleCharge;
    @SerializedName("rescheduleTime")
    @Expose
    private String rescheduleTime;
    @SerializedName("isCounterOnly")
    @Expose
    private Object isCounterOnly;
    @SerializedName("amnt")
    @Expose
    private List<Integer> amnt = null;
    @SerializedName("maxfr")
    @Expose
    private Integer maxfr;
    @SerializedName("isOtg")
    @Expose
    private Boolean isOtg;
    @SerializedName("src")
    @Expose
    private Object src;
    @SerializedName("dst")
    @Expose
    private Object dst;
    @SerializedName("showGPSIcon")
    @Expose
    private Boolean showGPSIcon;
    @SerializedName("tns")
    @Expose
    private Integer tns;
    @SerializedName("bpData")
    @Expose
    private List<BpDatum> bpData = null;
    @SerializedName("dpData")
    @Expose
    private List<DpDatum> dpData = null;
    @SerializedName("zcf")
    @Expose
    private Boolean zcf;
    @SerializedName("zcfTime")
    @Expose
    private Object zcfTime;
    @SerializedName("bc")
    @Expose
    private Bc bc;
    @SerializedName("IsEticket")
    @Expose
    private Boolean isEticket;
    @SerializedName("isRtc")
    @Expose
    private Boolean isRtc;
    @SerializedName("CampaignType")
    @Expose
    private List<Object> campaignType = null;
    @SerializedName("StdBp")
    @Expose
    private String stdBp;
    @SerializedName("StdDp")
    @Expose
    private String stdDp;
    @SerializedName("cityexpress")
    @Expose
    private Boolean cityexpress;
    @SerializedName("viacity")
    @Expose
    private String viacity;
    @SerializedName("StdBpTime")
    @Expose
    private Integer stdBpTime;
    @SerializedName("StdDpTime")
    @Expose
    private Integer stdDpTime;
    @SerializedName("StdBpFullTime")
    @Expose
    private String stdBpFullTime;
    @SerializedName("StdDpFullTime")
    @Expose
    private String stdDpFullTime;
    @SerializedName("Duration")
    @Expose
    private Integer duration;
    @SerializedName("subTypeId")
    @Expose
    private Integer subTypeId;
    @SerializedName("IternaryId")
    @Expose
    private Integer iternaryId;
    @SerializedName("tripRoute")
    @Expose
    private Object tripRoute;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getBt() {
        return bt;
    }

    public void setBt(String bt) {
        this.bt = bt;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public Rt getRt() {
        return rt;
    }

    public void setRt(Rt rt) {
        this.rt = rt;
    }

    public Object getOpRtg() {
        return opRtg;
    }

    public void setOpRtg(Object opRtg) {
        this.opRtg = opRtg;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public Integer getDm() {
        return dm;
    }

    public void setDm(Integer dm) {
        this.dm = dm;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public Integer getAm() {
        return am;
    }

    public void setAm(Integer am) {
        this.am = am;
    }

    public Integer getDur() {
        return dur;
    }

    public void setDur(Integer dur) {
        this.dur = dur;
    }

    public Boolean getIssa() {
        return issa;
    }

    public void setIssa(Boolean issa) {
        this.issa = issa;
    }

    public Boolean getIscs() {
        return iscs;
    }

    public void setIscs(Boolean iscs) {
        this.iscs = iscs;
    }

    public Boolean getIslt() {
        return islt;
    }

    public void setIslt(Boolean islt) {
        this.islt = islt;
    }

    public String getTz() {
        return tz;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }

    public Boolean getIsbd() {
        return isbd;
    }

    public void setIsbd(Boolean isbd) {
        this.isbd = isbd;
    }

    public String getSi() {
        return si;
    }

    public void setSi(String si) {
        this.si = si;
    }

    public Integer getBsi() {
        return bsi;
    }

    public void setBsi(Integer bsi) {
        this.bsi = bsi;
    }

    public String getVt() {
        return vt;
    }

    public void setVt(String vt) {
        this.vt = vt;
    }

    public List<Integer> getFrLst() {
        return frLst;
    }

    public void setFrLst(List<Integer> frLst) {
        this.frLst = frLst;
    }

    public Integer getMinfr() {
        return minfr;
    }

    public void setMinfr(Integer minfr) {
        this.minfr = minfr;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Boolean getIsmt() {
        return ismt;
    }

    public void setIsmt(Boolean ismt) {
        this.ismt = ismt;
    }

    public String getTvs() {
        return tvs;
    }

    public void setTvs(String tvs) {
        this.tvs = tvs;
    }

    public P42 getP42() {
        return p42;
    }

    public void setP42(P42 p42) {
        this.p42 = p42;
    }

    public Integer getSpt() {
        return spt;
    }

    public void setSpt(Integer spt) {
        this.spt = spt;
    }

    public Integer getWnSt() {
        return wnSt;
    }

    public void setWnSt(Integer wnSt) {
        this.wnSt = wnSt;
    }

    public Integer getSSt() {
        return sSt;
    }

    public void setSSt(Integer sSt) {
        this.sSt = sSt;
    }

    public Integer getNsa() {
        return nsa;
    }

    public void setNsa(Integer nsa) {
        this.nsa = nsa;
    }

    public Double getBs() {
        return bs;
    }

    public void setBs(Double bs) {
        this.bs = bs;
    }

    public String getCur() {
        return cur;
    }

    public void setCur(String cur) {
        this.cur = cur;
    }

    public String getLp() {
        return lp;
    }

    public void setLp(String lp) {
        this.lp = lp;
    }

    public Integer getIgId() {
        return igId;
    }

    public void setIgId(Integer igId) {
        this.igId = igId;
    }

    public Boolean getIsPc() {
        return isPc;
    }

    public void setIsPc(Boolean isPc) {
        this.isPc = isPc;
    }

    public Integer getBusTypeId() {
        return busTypeId;
    }

    public void setBusTypeId(Integer busTypeId) {
        this.busTypeId = busTypeId;
    }

    public Object getCmpg() {
        return cmpg;
    }

    public void setCmpg(Object cmpg) {
        this.cmpg = cmpg;
    }

    public String getFirstBpTime() {
        return firstBpTime;
    }

    public void setFirstBpTime(String firstBpTime) {
        this.firstBpTime = firstBpTime;
    }

    public Boolean getIsso() {
        return isso;
    }

    public void setIsso(Boolean isso) {
        this.isso = isso;
    }

    public Boolean getIsRescheduled() {
        return isRescheduled;
    }

    public void setIsRescheduled(Boolean isRescheduled) {
        this.isRescheduled = isRescheduled;
    }

    public Object getRescheduleCharge() {
        return rescheduleCharge;
    }

    public void setRescheduleCharge(Object rescheduleCharge) {
        this.rescheduleCharge = rescheduleCharge;
    }

    public String getRescheduleTime() {
        return rescheduleTime;
    }

    public void setRescheduleTime(String rescheduleTime) {
        this.rescheduleTime = rescheduleTime;
    }

    public Object getIsCounterOnly() {
        return isCounterOnly;
    }

    public void setIsCounterOnly(Object isCounterOnly) {
        this.isCounterOnly = isCounterOnly;
    }

    public List<Integer> getAmnt() {
        return amnt;
    }

    public void setAmnt(List<Integer> amnt) {
        this.amnt = amnt;
    }

    public Integer getMaxfr() {
        return maxfr;
    }

    public void setMaxfr(Integer maxfr) {
        this.maxfr = maxfr;
    }

    public Boolean getIsOtg() {
        return isOtg;
    }

    public void setIsOtg(Boolean isOtg) {
        this.isOtg = isOtg;
    }

    public Object getSrc() {
        return src;
    }

    public void setSrc(Object src) {
        this.src = src;
    }

    public Object getDst() {
        return dst;
    }

    public void setDst(Object dst) {
        this.dst = dst;
    }

    public Boolean getShowGPSIcon() {
        return showGPSIcon;
    }

    public void setShowGPSIcon(Boolean showGPSIcon) {
        this.showGPSIcon = showGPSIcon;
    }

    public Integer getTns() {
        return tns;
    }

    public void setTns(Integer tns) {
        this.tns = tns;
    }

    public List<BpDatum> getBpData() {
        return bpData;
    }

    public void setBpData(List<BpDatum> bpData) {
        this.bpData = bpData;
    }

    public List<DpDatum> getDpData() {
        return dpData;
    }

    public void setDpData(List<DpDatum> dpData) {
        this.dpData = dpData;
    }

    public Boolean getZcf() {
        return zcf;
    }

    public void setZcf(Boolean zcf) {
        this.zcf = zcf;
    }

    public Object getZcfTime() {
        return zcfTime;
    }

    public void setZcfTime(Object zcfTime) {
        this.zcfTime = zcfTime;
    }

    public Bc getBc() {
        return bc;
    }

    public void setBc(Bc bc) {
        this.bc = bc;
    }

    public Boolean getIsEticket() {
        return isEticket;
    }

    public void setIsEticket(Boolean isEticket) {
        this.isEticket = isEticket;
    }

    public Boolean getIsRtc() {
        return isRtc;
    }

    public void setIsRtc(Boolean isRtc) {
        this.isRtc = isRtc;
    }

    public List<Object> getCampaignType() {
        return campaignType;
    }

    public void setCampaignType(List<Object> campaignType) {
        this.campaignType = campaignType;
    }

    public String getStdBp() {
        return stdBp;
    }

    public void setStdBp(String stdBp) {
        this.stdBp = stdBp;
    }

    public String getStdDp() {
        return stdDp;
    }

    public void setStdDp(String stdDp) {
        this.stdDp = stdDp;
    }

    public Boolean getCityexpress() {
        return cityexpress;
    }

    public void setCityexpress(Boolean cityexpress) {
        this.cityexpress = cityexpress;
    }

    public String getViacity() {
        return viacity;
    }

    public void setViacity(String viacity) {
        this.viacity = viacity;
    }

    public Integer getStdBpTime() {
        return stdBpTime;
    }

    public void setStdBpTime(Integer stdBpTime) {
        this.stdBpTime = stdBpTime;
    }

    public Integer getStdDpTime() {
        return stdDpTime;
    }

    public void setStdDpTime(Integer stdDpTime) {
        this.stdDpTime = stdDpTime;
    }

    public String getStdBpFullTime() {
        return stdBpFullTime;
    }

    public void setStdBpFullTime(String stdBpFullTime) {
        this.stdBpFullTime = stdBpFullTime;
    }

    public String getStdDpFullTime() {
        return stdDpFullTime;
    }

    public void setStdDpFullTime(String stdDpFullTime) {
        this.stdDpFullTime = stdDpFullTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getSubTypeId() {
        return subTypeId;
    }

    public void setSubTypeId(Integer subTypeId) {
        this.subTypeId = subTypeId;
    }

    public Integer getIternaryId() {
        return iternaryId;
    }

    public void setIternaryId(Integer iternaryId) {
        this.iternaryId = iternaryId;
    }

    public Object getTripRoute() {
        return tripRoute;
    }

    public void setTripRoute(Object tripRoute) {
        this.tripRoute = tripRoute;
    }

}
