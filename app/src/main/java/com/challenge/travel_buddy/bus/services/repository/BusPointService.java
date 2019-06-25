package com.challenge.travel_buddy.bus.services.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.challenge.travel_buddy.train.services.model.SearchStationModel;
import com.challenge.travel_buddy.train.services.repository.StationService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;


public interface BusPointService {

    @Headers({"apikey:ixiweb!2$"})
    @GET("trainstation?searchFor=trainstations&anchor=false")
    Call<List<SearchStationModel>> getStation(
            @Query("value") String value
    );
}