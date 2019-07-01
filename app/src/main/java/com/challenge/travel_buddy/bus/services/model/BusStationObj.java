package com.challenge.travel_buddy.bus.services.model;

public class BusStationObj {

    public ResHeaderModel responseHeader;
    public BusStationResp response;

    public ResHeaderModel getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(ResHeaderModel responseHeader) {
        this.responseHeader = responseHeader;
    }

    public BusStationResp getResponse() {
        return response;
    }

    public void setResponse(BusStationResp response) {
        this.response = response;
    }
}
