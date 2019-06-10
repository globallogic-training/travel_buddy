package com.challenge.travel_buddy.train.trainsearch.services.repository;

import com.challenge.travel_buddy.train.trainsearch.services.model.Train;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TrainSearchService {

    @Headers({"apikey:ixiweb!2$"})
    @GET("trains/v1/search/between/{from}/{to}?")
    Call<Train> getTrains(
            @Path("from") String from,
            @Path("to") String to,
            @Query("date") String date
    );

}
