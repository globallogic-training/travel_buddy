package com.challenge.travel_buddy.train.trainsearch.services.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Map;

public class TrainDataModel {
    @SerializedName("embarkId")
    private String embarkId;
    @SerializedName("board")
    private String board;
    @SerializedName("boardStation")
    private String boardStation;
    @SerializedName("departDate")
    private String departDate;
    @SerializedName("arrivalDate")
    private String arrivalDate;
    @SerializedName("distance")
    private String distance;
    @SerializedName("stationsElapsed")
    private String stationsElapsed;
    @SerializedName("attributes")
    private TrainAtrributesModel attributes;
    @SerializedName("originNames")
    private ArrayList<StationModel> originNames;
    @SerializedName("destinationNames")
    private ArrayList<StationModel> destinationNames;
    @SerializedName("deboardStation")
    private String deboardStation;
    @SerializedName("deboard")
    private String deboard;
    private Map<String, Available_Status> available_seats;


   /* public TrainModel(String embarkId, String board, String boardStation, String departDate,
                      String arrivalDate, String distance, String stationsElapsed,
                      *//*TrainAtrributesModel attributes, *//*StationModel originNames,
                      StationModel destinationNames, String deboardStation, String deboard)
    {
        this.embarkId = embarkId;
        this.board = board;
        this.boardStation = boardStation;
        this.departDate = departDate;
        this.arrivalDate = arrivalDate;
        this.distance = distance;
        this.stationsElapsed = stationsElapsed;
        this.attributes = attributes;
        this.originNames = originNames;
        this.destinationNames = destinationNames;
        this.deboardStation = deboardStation;
        this.deboard = deboard;
    } */

    public String getEmbarkId() {
        return embarkId;
    }

    public void setEmbarkId(String embarkId) {
        this.embarkId = embarkId;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getBoardStation() {
        return boardStation;
    }

    public void setBoardStation(String boardStation) {
        this.boardStation = boardStation;
    }

    public String getDepartDate() {
        return departDate;
    }

    public void setDepartDate(String departDate) {
        this.departDate = departDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getStationsElapsed() {
        return stationsElapsed;
    }

    public void setStationsElapsed(String stationsElapsed) {
        this.stationsElapsed = stationsElapsed;
    }

    public TrainAtrributesModel getAttributes() {
        return attributes;
    }

    public void setAttributes(TrainAtrributesModel attributes) {
        this.attributes = attributes;
    }

    public ArrayList<StationModel> getOriginNames() {
        return originNames;
    }

    public void setOriginNames(ArrayList<StationModel> originNames) {
        this.originNames = originNames;
    }

    public ArrayList<StationModel> getDestinationNames() {
        return destinationNames;
    }

    public void setDestinationNames(ArrayList<StationModel> destinationNames) {
        this.destinationNames = destinationNames;
    }

    public String getDeboardStation() {
        return deboardStation;
    }

    public void setDeboardStation(String deboardStation) {
        this.deboardStation = deboardStation;
    }

    public String getDeboard() {
        return deboard;
    }

    public void setDeboard(String deboard) {
        this.deboard = deboard;
    }

    public Map<String, Available_Status> getAvailable_seats() {
        return available_seats;
    }

    public void setAvailable_seats(Map<String, Available_Status> available_seats) {
        this.available_seats = available_seats;
    }
}
