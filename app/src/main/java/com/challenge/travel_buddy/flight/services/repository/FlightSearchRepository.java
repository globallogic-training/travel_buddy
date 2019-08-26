package com.challenge.travel_buddy.flight.services.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.challenge.travel_buddy.flight.services.model.Airport;
import com.challenge.travel_buddy.flight.services.model.Datum;
import com.challenge.travel_buddy.flight.services.model.Flight.FlightData;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlightSearchRepository {

    private AirportService airportService;

    @Inject
    public FlightSearchRepository(AirportService airportService) {
        this.airportService = airportService;
    }

    public LiveData<List<Datum>> getBusPoint(String value){
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

    public LiveData<FlightData> getFlight(String source, String destination, String startDate, String endDate){
        final MutableLiveData<FlightData> data = new MutableLiveData<>();
        airportService.getFlightList(source ,destination,startDate, endDate)
                .enqueue(new Callback<FlightData>() {
                    @Override
                    public void onResponse(Call<FlightData> call, Response<FlightData> response) {
                        data.setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<FlightData> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
}