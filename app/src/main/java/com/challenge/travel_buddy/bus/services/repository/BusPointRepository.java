package com.challenge.travel_buddy.bus.services.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.challenge.travel_buddy.bus.services.model.BusPoint;
import com.challenge.travel_buddy.train.services.model.SearchStationModel;
import com.challenge.travel_buddy.train.trainsearch.services.model.TrainAvailabilityModel;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParseException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusPointRepository { private BusPointService busPointService;

    @Inject
    public BusPointRepository(BusPointService busPointService) {
        this.busPointService = busPointService;
    }

    public LiveData<List<BusPoint>> getBusPoint(String value){
        final MutableLiveData<List<BusPoint>> data = new MutableLiveData<>();
        busPointService.getStation(value)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.body() != null && response.code() == 200) {
                            String res = null;
                            Gson gson = new Gson();
                            Gson gson1 = new Gson();
                            try {
                                res = response.body().string();

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                JSONObject jsonObject = new JSONObject(res);
                                JSONObject responseObj = (JSONObject) jsonObject.get("response");
                                JSONArray docsArray  = (JSONArray) responseObj.get("docs");
//                                data.setValue((List<BusPoint>) docsArray);
//                                gson.fromJson(docsArray);
                                System.out.println("Kismi Bar");

//                                TrainAvailabilityModel trainAvailabilityModel =  new ObjectMapper().readValue(String.valueOf(jsonObject), TrainAvailabilityModel.class);
//                                trainAvailabilityModel.setDate(searchDate);
//                                data.add(trainAvailabilityModel);
//                                bestAvailTrainInMonth.setValue(data);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (JsonParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
}
