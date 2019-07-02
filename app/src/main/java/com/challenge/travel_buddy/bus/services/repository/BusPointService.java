package com.challenge.travel_buddy.bus.services.repository;



import com.challenge.travel_buddy.bus.services.model.BusStationObj;
import com.challenge.travel_buddy.bus.services.model.busresponse.BusSearchResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface BusPointService {

//    @Headers({"apikey:ixiweb!2$"})
    @GET("/Home/SolarSearch?parentLocationId=0&parentId=0&parentLocationType=")
    Call<BusStationObj> getStation(
            @Query("search") String value
    );

    @POST("/search/SearchResults?sectionId=0&groupId=0&limit=0&offset=0&sort=0&sortOrder=0&meta=true&returnSearch=0")
    Call<BusSearchResponse> getAllBuses(
            @Header("Content-Type") String type,
            @Query("fromCity") String fromStationId,
            @Query("toCity") String toStationId,
            @Query("src") String from,
            @Query("dst") String to,
            @Query("DOj") String DOJ

    );
}