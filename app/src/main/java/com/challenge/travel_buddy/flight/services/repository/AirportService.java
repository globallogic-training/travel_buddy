package com.challenge.travel_buddy.flight.services.repository;

import com.challenge.travel_buddy.flight.services.model.AirportResponse;
import com.challenge.travel_buddy.flight.services.model.Flight.Data;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AirportService {
    @GET("/api/v1/flights_search/find_node_by_name_v2/?limit=10")
    Call<AirportResponse> getAirport(
            @Query("search_query") String value
    );

    @Headers({"apikey:ixiweb!2$"})
    @GET("/api/flights/search?return=&adults=1&children=0&infants=0&class=e&version=2.0&searchSrc=ixibook")
    Call<ResponseBody> getSearchProvider(
            @Query("origin") String origin,
            @Query("destination") String destination,
            @Query("leave") String leave
    );

    @Headers({"apikey:ixiweb!2$"})
    @GET("/api/flights/search/poll/{searchToken}?searchProviderIds=196,12,1020,1021")
    Call<Data> getFlights(
            @Path("searchToken") String searchToken
    );

}