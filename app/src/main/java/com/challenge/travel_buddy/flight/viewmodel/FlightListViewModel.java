package com.challenge.travel_buddy.flight.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.challenge.travel_buddy.flight.services.model.Flight.FlightData;
import com.challenge.travel_buddy.flight.services.repository.FlightSearchRepository;

import javax.inject.Inject;

public class FlightListViewModel extends AndroidViewModel {

    private FlightSearchRepository flightSearchRepository;
    private LiveData<FlightData> flightData;
//    private LiveData<Map<Date, List<Inv>>> fututeBuses;


    @Inject
    public FlightListViewModel(@NonNull FlightSearchRepository flightSearchRepository, @NonNull Application application) {
        super(application);
        this.flightSearchRepository = flightSearchRepository;
    }

    public LiveData<FlightData> getFlight(String source, String destination, String startDate, String endDate){
        if(flightData == null){
            flightData =  flightSearchRepository.getFlight(source, destination, startDate, endDate);
        }
        return flightData;
    }
//
//    public LiveData<Map<Date, List<Inv>>> getFutureBuses(String from, String to, String fromStationId, String toStationId, String DOJ){
//        fututeBuses = busPointRepository.getFutureBuses(from, to, fromStationId, toStationId,DOJ);
//        return fututeBuses;
//    }
}

