package com.challenge.travel_buddy.train.trainsearch.services.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.challenge.travel_buddy.train.trainsearch.di.TrainSearchActivityScope;
import com.challenge.travel_buddy.train.trainsearch.services.model.Train;

import javax.inject.Inject;

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
}
