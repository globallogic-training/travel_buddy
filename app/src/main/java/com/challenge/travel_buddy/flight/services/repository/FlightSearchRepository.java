package com.challenge.travel_buddy.flight.services.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.challenge.travel_buddy.flight.services.model.Airport;
import com.challenge.travel_buddy.flight.services.model.AirportResponse;
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
    private FlightService flightService;
    private final MutableLiveData<Map<String, Object>> data = new MutableLiveData<>();

    @Inject
    public FlightSearchRepository(AirportService airportService, FlightService flightService) {
        this.airportService = airportService;
        this.flightService = flightService;
    }

    public LiveData<List<Airport>> getAirportLists(String value){
        final MutableLiveData<List<Airport>> data = new MutableLiveData<>();
        airportService.getAirport(value)
                .enqueue(new Callback<AirportResponse>() {
                    @Override
                    public void onResponse(Call<AirportResponse> call, Response<AirportResponse> response) {
                        data.setValue(response.body().getData().getAirport());
                    }

                    @Override
                    public void onFailure(Call<AirportResponse> call, Throwable t) {
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
                        }else{
                            data.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }

    public LiveData<Map<String, Object>> getFlights(String flyFrom, String to, String dateFrom, String dateTo){


        flightService.getFlights(flyFrom, to, dateFrom, dateTo)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.body() != null && response.code() == 200) {
                            String res = null;
                            try {
                                res = response.body().string();
                                JSONObject jsonObject = new JSONObject(res);
                                Map<String,Object> flightData =  new ObjectMapper().readValue(String.valueOf(jsonObject), Map.class);
                                data.setValue(flightData);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (JsonParseException e) {
                                e.printStackTrace();
                            } catch (JsonMappingException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }else{
                            data.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }

    public LiveData<Map<String, Object>> getCheapestFlight(String flyFrom, String to, String dateFrom, String dateTo){
        final MutableLiveData<Map<String, Object>> data = new MutableLiveData<>();

        flightService.getFlights(flyFrom, to, dateFrom, dateTo)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.body() != null && response.code() == 200) {
                            String res = null;
                            try {
                                res = response.body().string();
                                JSONObject jsonObject = new JSONObject(res);
                                Map<String,Object> flightData =  new ObjectMapper().readValue(String.valueOf(jsonObject), Map.class);

                                List<Map<String, Object>> flights = (List) flightData.get("data");
                                Map<String,Object> cheapestFlight = flights.get(0);
                                double minPrice =  flights.get(0).get("price") instanceof Integer ?
                                        (int) flights.get(0).get("price") : (double) flights.get(0).get("price");
                                for ( Map<String, Object> flight: flights){
                                    double tempPrice =  flight.get("price") instanceof Integer ?
                                            (int) flight.get("price") : (double) flight.get("price");
                                    if(minPrice > tempPrice){
                                        minPrice = tempPrice;
                                        cheapestFlight = flight;
                                    }
                                }
                                data.setValue(cheapestFlight);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (JsonParseException e) {
                                e.printStackTrace();
                            } catch (JsonMappingException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }else{
                            data.setValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
}