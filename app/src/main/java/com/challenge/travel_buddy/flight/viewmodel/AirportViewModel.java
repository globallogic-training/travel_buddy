package com.challenge.travel_buddy.flight.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.challenge.travel_buddy.bus.services.repository.BusPointRepository;
import com.challenge.travel_buddy.flight.services.model.AirportModel;
import com.challenge.travel_buddy.flight.services.repository.AirportRepository;

import java.util.List;

import javax.inject.Inject;

public class AirportViewModel extends AndroidViewModel {

    private AirportRepository airportRepository;
    private LiveData<List<AirportModel>> searchStationModelLiveData;
//    private final MutableLiveData<String> projectID;

//    public ObservableField<> project = new ObservableField<>();

    @Inject
    public AirportViewModel(@NonNull AirportRepository airportRepository, @NonNull Application application) {
        super(application);
        this.airportRepository = airportRepository;
    }

    public LiveData<List<AirportModel>> searchAirport(String val){
        searchStationModelLiveData = airportRepository.getBusPoint(val);
        return searchStationModelLiveData;
    }
}
