package com.challenge.travel_buddy.bus.services.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.challenge.travel_buddy.bus.services.model.BusModel;
import com.challenge.travel_buddy.bus.services.model.BusStationObj;
import com.challenge.travel_buddy.bus.services.model.BusStationResp;
import com.challenge.travel_buddy.train.trainsearch.services.model.TrainAvailabilityModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusPointRepository {

    @Inject
    public BusPointRepository(BusPointService busPointService) {
        this.busPointService = busPointService;
    }

    private BusPointService busPointService;

    public LiveData<List<BusModel>> getBusPoint(String value){
        final MutableLiveData<List<BusModel>> data = new MutableLiveData<>();
        busPointService.getStation(value)
                .enqueue(new Callback<BusStationObj>() {
                    @Override
                    public void onResponse(Call<BusStationObj> call, Response<BusStationObj> response) {
                        if(response.body() != null && response.body().response !=null){
                            if(response.body().response.docs.size() > 0){
                                data.setValue(response.body().response.docs);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<BusStationObj> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
}
