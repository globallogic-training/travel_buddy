package com.challenge.travel_buddy.flight.services.repository;

import com.challenge.travel_buddy.flight.services.model.Airport;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AirportService {
    //    @Headers({"apikey:ixiweb!2$"})
    @GET("/api/v1/flights_search/find_node_by_name_v2/?limit=10")
    Call<Airport> getAirport(
            @Query("search_query") String value
    );
}