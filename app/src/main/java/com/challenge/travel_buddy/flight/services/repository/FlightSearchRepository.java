package com.challenge.travel_buddy.flight.services.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.challenge.travel_buddy.flight.services.model.Airport;
import com.challenge.travel_buddy.flight.services.model.Datum;
import com.challenge.travel_buddy.flight.services.model.Flight.Data;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParseException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlightSearchRepository {

    private AirportService airportService;

    @Inject
    public FlightSearchRepository(AirportService airportService) {
        this.airportService = airportService;
    }

    public LiveData<List<Datum>> getAirportLists(String value){
        final MutableLiveData<List<Datum>> data = new MutableLiveData<>();
        airportService.getAirport(value)
                .enqueue(new Callback<Airport>() {
                    @Override
                    public void onResponse(Call<Airport> call, Response<Airport> response) {
                        data.setValue(response.body().getData());
                    }

                    @Override
                    public void onFailure(Call<Airport> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }

    public LiveData<Map<String,Object>> getSearchProvider(String source, String destination, String startDate, String endDate){
        final MutableLiveData<Map<String,Object>> data = new MutableLiveData<>();
        airportService.getSearchProvider(source ,destination,startDate)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.body() != null && response.code() == 200) {
                            String res = null;
                            try {
                                res = response.body().string();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                JSONObject jsonObject = new JSONObject(res);
                                Map<String,Object> searchProvider =  new ObjectMapper().readValue(String.valueOf(jsonObject), Map.class);
                                data.setValue(searchProvider);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (JsonParseException e) {
                                e.printStackTrace();
                            } catch (JsonMappingException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            System.out.println("Map");
                        }else{
                            data.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("error", t.toString());
                        data.setValue(null);
                    }
                });
        return data;
    }

    public LiveData<Data> getFlights(String value){
        final MutableLiveData<Data> data = new MutableLiveData<>();
        airportService.getFlights(value)
                .enqueue(new Callback<Data>() {
                    @Override
                    public void onResponse(Call<Data> call, Response<Data> response) {
                        data.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<Data> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
}