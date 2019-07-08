package com.challenge.travel_buddy.bus.services.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.challenge.travel_buddy.bus.services.model.BusModel;
import com.challenge.travel_buddy.bus.services.model.BusStationObj;
import com.challenge.travel_buddy.bus.services.model.busresponse.BusSearchResponse;
import com.challenge.travel_buddy.bus.services.model.busresponse.Inv;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusPointRepository {

    @Inject
    public BusPointRepository(BusPointService busPointService) {
        this.busPointService = busPointService;
    }

    private BusPointService busPointService;
    public final int THREAD_COUNT = 15;

    MutableLiveData<Map<Date, List<Inv>>> liveMapInvlist = new MutableLiveData<>();
    Map<Date,List<Inv>> mapListInv = new TreeMap<>();

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

    public MutableLiveData<List<Inv>> getAllBuses(String from, String to, String fromStationId, String toStationId, String DOJ){
        final MutableLiveData<List<Inv>> data = new MutableLiveData<>();
        busPointService.getAllBuses("application/json",fromStationId,toStationId,from,to,DOJ)
                .enqueue(new Callback<BusSearchResponse>() {
                    @Override
                    public void onResponse(Call<BusSearchResponse> call, Response<BusSearchResponse> response) {
                        if(response.body() != null && response.body().getInv().size() > 0){
                            data.setValue(response.body().getInv());
                        }
                    }

                    @Override
                    public void onFailure(Call<BusSearchResponse> call, Throwable t) {
                        Log.d("rr", t.getMessage());
                    }
                });
        return data;
    }

    public LiveData<Map<Date, List<Inv>>> getFutureBuses(String from, String to, String fromStationId, String toStationId, String DOJ){

        String threadDate = DOJ;
        Date orignalDate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            orignalDate = formatter.parse(DOJ);
            System.out.println(orignalDate);
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        for(int i=0; i< THREAD_COUNT; i++){
            if(i > 0){
                Calendar cal = Calendar.getInstance();
                cal.setTime(orignalDate);
                cal.add(Calendar.DATE, 1);
                orignalDate = cal.getTime();
                SimpleDateFormat dateToString = new SimpleDateFormat("dd-MMM-yyyy");
                try{
                    threadDate = dateToString.format(orignalDate);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            getBusData(from, to, fromStationId, toStationId, threadDate, orignalDate);


        }

        return liveMapInvlist;
    }

    void getBusData(String from, String to, String fromStationId, String toStationId, String DOJ, Date currentDate){
        Runnable trainAvailThread = new Runnable() {
            @Override
            public void run() {
                busPointService.getAllBuses("application/json",fromStationId,toStationId,from,to,DOJ)
                        .enqueue(new Callback<BusSearchResponse>() {
                            @Override
                            public void onResponse(Call<BusSearchResponse> call, Response<BusSearchResponse> response) {
                                if(response.body() != null && response.body().getInv().size() > 0){

                                    mapListInv.put(currentDate, response.body().getInv());
                                    if(mapListInv.size() == THREAD_COUNT){
                                        liveMapInvlist.setValue(mapListInv);
                                    }

                                }
                            }

                            @Override
                            public void onFailure(Call<BusSearchResponse> call, Throwable t) {
                                Log.d("rr", t.getMessage());
                                mapListInv.put(currentDate, null);
                            }
                        });
            }

        };

        Thread t = new Thread(trainAvailThread);
        t.run();

    }
}

