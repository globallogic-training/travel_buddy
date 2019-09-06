package com.challenge.travel_buddy.flight.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.challenge.travel_buddy.flight.helper.FlightHelper;
import com.challenge.travel_buddy.flight.services.model.Flight.Data;
import com.challenge.travel_buddy.flight.services.repository.FlightSearchRepository;

import java.util.Map;

import javax.inject.Inject;

public class FlightListViewModel extends AndroidViewModel {

    private FlightSearchRepository flightSearchRepository;
    private LiveData<Map<String,Object>> searchData;
    private LiveData<Map<String, Object>> flightData;
    private MutableLiveData<String> searchDate;
    private LiveData<Map<String,Object>> cheapestFlight;



    @Inject
    public FlightListViewModel(@NonNull FlightSearchRepository flightSearchRepository, @NonNull Application application) {
        super(application);
        this.flightSearchRepository = flightSearchRepository;
        this.searchDate = new MutableLiveData<>();
    }

    public LiveData<Map<String, Object>> getFlightData(String flyFrom, String to, String dateFrom, String dateTo){
            flightData =  flightSearchRepository.getFlights(flyFrom, to, dateFrom, dateTo);
        return flightData;
    }

    public LiveData<Map<String, Object>> getCheapestFlightData(String flyFrom, String to, String dateFrom, String dateTo){
        flightData =  flightSearchRepository.getCheapestFlight(flyFrom, to, dateFrom, FlightHelper.getIncrementedDate(dateFrom));
        return flightData;
    }

    public LiveData<Map<String, Object>> getSearchProviders(String source, String destination, String startDate, String endDate){
        searchData = flightSearchRepository.getSearchProvider(source, destination, startDate, endDate);
        return searchData;
    }

    public void setSearchDate(String newDate){
        searchDate.setValue(newDate);
    }

    public MutableLiveData<String> getSearchDate(){
        return searchDate;
    }
}

