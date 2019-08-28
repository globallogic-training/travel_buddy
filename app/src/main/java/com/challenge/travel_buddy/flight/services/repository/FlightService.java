package com.challenge.travel_buddy.flight.services.repository;

import com.challenge.travel_buddy.flight.services.model.Airport;
import com.challenge.travel_buddy.flight.services.model.Flight.Data;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FlightService {

//    @Headers({"apikey:ixiweb!2$"})
    @GET("/flights?partner=KIWI&curr=INR")
    Call<ResponseBody> getFlights(
            @Query("flyFrom") String flyFrom,
            @Query("to") String to,
            @Query("dateFrom") String dateFrom,
            @Query("dateTo") String dateTo
    );

}
