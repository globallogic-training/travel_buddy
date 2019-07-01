
package com.challenge.travel_buddy.bus.services.model.busresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PriceDescriptor {

    @SerializedName("19")
    @Expose
    private String _19;
    @SerializedName("25")
    @Expose
    private String _25;
    @SerializedName("40")
    @Expose
    private String _40;

    public String get19() {
        return _19;
    }

    public void set19(String _19) {
        this._19 = _19;
    }

    public String get25() {
        return _25;
    }

    public void set25(String _25) {
        this._25 = _25;
    }

    public String get40() {
        return _40;
    }

    public void set40(String _40) {
        this._40 = _40;
    }

}
