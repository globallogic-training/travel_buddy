package com.challenge.travel_buddy.train.trainsearch.services.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.challenge.travel_buddy.train.trainsearch.di.TrainSearchActivityScope;
import com.challenge.travel_buddy.train.trainsearch.services.model.Available_Status;
import com.challenge.travel_buddy.train.trainsearch.services.model.Train;
import com.challenge.travel_buddy.train.trainsearch.services.model.TrainAvailabilityModel;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParseException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@TrainSearchActivityScope
public class TrainSearchRepository {
    private TrainSearchService trainSearchService;

    @Inject
    public TrainSearchRepository(TrainSearchService mTrainSearchService) {
        this.trainSearchService = mTrainSearchService;
    }

    public LiveData<Train> getTrains(String from, String to, String date){
        final MutableLiveData<Train> data = new MutableLiveData<>();
        this.trainSearchService.getTrains(from,to,date)
                .enqueue(new Callback<Train>() {
                    @Override
                    public void onResponse(Call<Train> call, Response<Train> response) {
                        if(response.body() != null){
                            Log.d("response", response.body().toString());
                            data.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Train> call, Throwable t) {
                        data.setValue(null);
                        Log.i("er","rr");
                    }
                });
        return data;
    }


    public LiveData<Map <String, Map<String, Available_Status>>> getAvailability(String from, String to, String date){
        final MutableLiveData<Map <String, Map<String, Available_Status>>> seatAvail = new MutableLiveData<>();
        this.trainSearchService.getAvailability(from,to, date)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.body() != null && response.code() == 200) {
                            String res = null;
                            try {
                                res = response.body().string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                JSONObject jsonObject = new JSONObject(res);
                                List<Map<String,Available_Status>> seatData = new ArrayList<>();
                                TrainAvailabilityModel trainAvailabilityModel =  new ObjectMapper().readValue(String.valueOf(jsonObject), TrainAvailabilityModel.class);
                                Set<String> keys = trainAvailabilityModel.getData().keySet();
                                seatAvail.setValue(trainAvailabilityModel.getData());
//                                    Iterator<String> iterator = new trainAvailabilityModel.getData().
//                                Map<String, Available_Status> status = trainAvailabilityModel.getData().get(keys);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (JsonParseException e) {
                                e.printStackTrace();
                            } catch (JsonMappingException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("error", t.toString());
                    }
                });
        return seatAvail;
    }
}
