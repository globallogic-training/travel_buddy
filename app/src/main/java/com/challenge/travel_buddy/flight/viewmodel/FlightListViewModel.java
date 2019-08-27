package com.challenge.travel_buddy.flight.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.challenge.travel_buddy.flight.services.model.Flight.Data;
import com.challenge.travel_buddy.flight.services.repository.FlightSearchRepository;

import java.util.Map;

import javax.inject.Inject;

public class FlightListViewModel extends AndroidViewModel {

    private FlightSearchRepository flightSearchRepository;
    private LiveData<Map<String,Object>> searchData;
    private LiveData<Data> flightData;


    @Inject
    public FlightListViewModel(@NonNull FlightSearchRepository flightSearchRepository, @NonNull Application application) {
        super(application);
        this.flightSearchRepository = flightSearchRepository;
    }

    public LiveData<Data> getFlightData(String value){
            flightData =  flightSearchRepository.getFlights(value);
        return flightData;
    }

    public LiveData<Map<String, Object>> getSearchProviders(String source, String destination, String startDate, String endDate){
        searchData = flightSearchRepository.getSearchProvider(source, destination, startDate, endDate);
        return searchData;
    }
}

