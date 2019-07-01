package com.challenge.travel_buddy.flight.services.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.challenge.travel_buddy.flight.services.model.Airport;
import com.challenge.travel_buddy.flight.services.model.AirportModel;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AirportRepository {

    private AirportService airportService;

    @Inject
    public AirportRepository(AirportService airportService) {
        this.airportService = airportService;
    }

    public LiveData<List<AirportModel>> getBusPoint(String value){
        final MutableLiveData<List<AirportModel>> data = new MutableLiveData<>();
        airportService.getAirport(value)
                .enqueue(new Callback<Airport>() {
                    @Override
                    public void onResponse(Call<Airport> call, Response<Airport> response) {
                        System.out.println("Hello"+response);
                        data.setValue(response.body().getData().getR());
                    }

                    @Override
                    public void onFailure(Call<Airport> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
}