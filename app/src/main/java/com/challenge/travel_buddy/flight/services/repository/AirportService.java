package com.challenge.travel_buddy.flight.services.repository;

import com.challenge.travel_buddy.flight.services.model.Airport;
import com.challenge.travel_buddy.flight.services.model.Flight.FlightData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AirportService {
    //    @Headers({"apikey:ixiweb!2$"})
    @GET("/action/content/city?searchFor=airportSuggestions&nearByAirport=true")
    Call<Airport> getAirport(
            @Query("value") String value
    );

    @GET("/api/v2/graphs/data/new?currency=INR")
    Call<FlightData> getFlightList(
            @Query("origin") String origin,
            @Query("destination") String destination,
            @Query("startDate") String startDate,
            @Query("endDate") String endDate
    );

}