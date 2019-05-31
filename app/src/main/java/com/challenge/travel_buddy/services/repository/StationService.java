package com.challenge.travel_buddy.services.repository;

import com.challenge.travel_buddy.services.model.SearchStationModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StationService {
//    String HTTPS_API_GITHUB_URL = "https://api.github.com/";

    @Headers({"apikey:ixiweb!2$"})
    @GET("trainstation?searchFor=trainstations&anchor=false")
    Call<List<SearchStationModel>> getStation(
            @Query("value") String value
    );
}
