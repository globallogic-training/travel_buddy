package com.challenge.travel_buddy.bus.services.repository;



import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface BusPointService {

//    @Headers({"apikey:ixiweb!2$"})
    @GET("/Home/SolarSearch?parentLocationId=0&parentId=0&parentLocationType=")
    Call<ResponseBody> getStation(
            @Query("search") String value
    );
}