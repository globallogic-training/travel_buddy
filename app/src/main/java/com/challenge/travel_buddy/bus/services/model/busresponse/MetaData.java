
package com.challenge.travel_buddy.bus.services.model.busresponse;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MetaData {

    @SerializedName("totalCount")
    @Expose
    private Integer totalCount;
    @SerializedName("filtersUrl")
    @Expose
    private String filtersUrl;
    @SerializedName("blu")
    @Expose
    private String blu;
    @SerializedName("sections")
    @Expose
    private List<Section> sections = null;
    @SerializedName("sectionData")
    @Expose
    private SectionData sectionData;
    @SerializedName("nearByData")
    @Expose
    private List<Object> nearByData = null;
    @SerializedName("BusImageBaseURL")
    @Expose
    private String busImageBaseURL;
    @SerializedName("minFr")
    @Expose
    private Integer minFr;
    @SerializedName("maxFr")
    @Expose
    private Integer maxFr;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("ignoreRedDeal")
    @Expose
    private Integer ignoreRedDeal;
    @SerializedName("showGoogleAds")
    @Expose
    private Boolean showGoogleAds;
    @SerializedName("isPerzSort")
    @Expose
    private Boolean isPerzSort;
    @SerializedName("packages")
    @Expose
    private Object packages;
    @SerializedName("packagesError")
    @Expose
    private Object packagesError;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getFiltersUrl() {
        return filtersUrl;
    }

    public void setFiltersUrl(String filtersUrl) {
        this.filtersUrl = filtersUrl;
    }

    public String getBlu() {
        return blu;
    }

    public void setBlu(String blu) {
        this.blu = blu;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public SectionData getSectionData() {
        return sectionData;
    }

    public void setSectionData(SectionData sectionData) {
        this.sectionData = sectionData;
    }

    public List<Object> getNearByData() {
        return nearByData;
    }

    public void setNearByData(List<Object> nearByData) {
        this.nearByData = nearByData;
    }

    public String getBusImageBaseURL() {
        return busImageBaseURL;
    }

    public void setBusImageBaseURL(String busImageBaseURL) {
        this.busImageBaseURL = busImageBaseURL;
    }

    public Integer getMinFr() {
        return minFr;
    }

    public void setMinFr(Integer minFr) {
        this.minFr = minFr;
    }

    public Integer getMaxFr() {
        return maxFr;
    }

    public void setMaxFr(Integer maxFr) {
        this.maxFr = maxFr;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getIgnoreRedDeal() {
        return ignoreRedDeal;
    }

    public void setIgnoreRedDeal(Integer ignoreRedDeal) {
        this.ignoreRedDeal = ignoreRedDeal;
    }

    public Boolean getShowGoogleAds() {
        return showGoogleAds;
    }

    public void setShowGoogleAds(Boolean showGoogleAds) {
        this.showGoogleAds = showGoogleAds;
    }

    public Boolean getIsPerzSort() {
        return isPerzSort;
    }

    public void setIsPerzSort(Boolean isPerzSort) {
        this.isPerzSort = isPerzSort;
    }

    public Object getPackages() {
        return packages;
    }

    public void setPackages(Object packages) {
        this.packages = packages;
    }

    public Object getPackagesError() {
        return packagesError;
    }

    public void setPackagesError(Object packagesError) {
        this.packagesError = packagesError;
    }

}
