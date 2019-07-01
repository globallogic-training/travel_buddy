
package com.challenge.travel_buddy.bus.services.model.busresponse;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BusSearchResponse {

    @SerializedName("metaData")
    @Expose
    private MetaData metaData;
    @SerializedName("inv")
    @Expose
    private List<Inv> inv = null;
    @SerializedName("sort")
    @Expose
    private Integer sort;
    @SerializedName("sortOrder")
    @Expose
    private Integer sortOrder;

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public List<Inv> getInv() {
        return inv;
    }

    public void setInv(List<Inv> inv) {
        this.inv = inv;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

}
