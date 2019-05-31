package com.challenge.travel_buddy.services.repository;



import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.challenge.travel_buddy.services.model.SearchStationModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class StationRepository {
    private StationService stationService;

    @Inject
    public StationRepository(StationService stationService) {
        this.stationService = stationService;
    }

    public LiveData<List<SearchStationModel>> getStation(String value){
        final MutableLiveData<List<SearchStationModel>> data = new MutableLiveData<>();
        stationService.getStation(value)
                .enqueue(new Callback<List<SearchStationModel>>() {
                    @Override
                    public void onResponse(Call<List<SearchStationModel>> call, Response<List<SearchStationModel>> response) {
                        if(response.body() != null){
                            data.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<SearchStationModel>> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }

}
