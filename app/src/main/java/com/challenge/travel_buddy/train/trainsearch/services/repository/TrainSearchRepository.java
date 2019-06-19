package com.challenge.travel_buddy.train.trainsearch.services.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.challenge.travel_buddy.train.trainsearch.di.TrainSearchActivityScope;
import com.challenge.travel_buddy.train.trainsearch.services.model.Available_Status;
import com.challenge.travel_buddy.train.trainsearch.services.model.Train;
import com.challenge.travel_buddy.train.trainsearch.services.model.TrainAvailabilityModel;
import com.challenge.travel_buddy.train.trainsearch.services.model.Train_Number_Search;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParseException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
     private static final int THREAD_COUNT  = 30;
     private String threadDate = "";
    List<TrainAvailabilityModel> data = new ArrayList<>();
    private MutableLiveData<List<TrainAvailabilityModel>> bestAvailTrainInMonth = new MutableLiveData<>();

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


    public MutableLiveData<List<TrainAvailabilityModel>> getBestTrainInMonth(String from, String to, String date){
        threadDate = date;
        Date orignalDate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        try {
            orignalDate = formatter.parse(date);
            System.out.println(orignalDate);
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        for(int i=0; i< THREAD_COUNT; i++){
            if(i> 0){
                Calendar cal = Calendar.getInstance();
                cal.setTime(orignalDate);
                cal.add(Calendar.DATE, 1);
                orignalDate = cal.getTime();
                SimpleDateFormat dateToString = new SimpleDateFormat("ddMMyyyy");
                try{
                    threadDate = dateToString.format(orignalDate);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            getAvailData(from,to, threadDate);
        }
        return bestAvailTrainInMonth;
    }

    public void getAvailData(String from, String to, String searchDate){
        Runnable trainAvailThread = new Runnable() {
            @Override
            public void run() {
                trainSearchService.getAvailability(from, to, searchDate)
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
                                        TrainAvailabilityModel trainAvailabilityModel =  new ObjectMapper().readValue(String.valueOf(jsonObject), TrainAvailabilityModel.class);
                                        trainAvailabilityModel.setDate(searchDate);
                                        data.add(trainAvailabilityModel);
                                        bestAvailTrainInMonth.setValue(data);
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
            }

        };

        Thread t = new Thread(trainAvailThread);
        t.run();
    }


    public MutableLiveData<Train_Number_Search> getTrainFromCode(String trainNumber){
        final MutableLiveData <Train_Number_Search> train = new MutableLiveData<>();
        this.trainSearchService.getTrainFromCode(trainNumber)
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
                                JSONArray jsonArray = new JSONArray(res);
                                JSONObject jsonObject =  jsonArray.getJSONObject(0);
                                Train_Number_Search serchTrainModel =  new ObjectMapper().readValue(String.valueOf(jsonObject), Train_Number_Search.class);
                                train.setValue(serchTrainModel);
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

                    }
                });
        return train;
    }
}
