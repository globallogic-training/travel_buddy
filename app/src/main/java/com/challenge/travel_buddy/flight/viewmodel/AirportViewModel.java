package com.challenge.travel_buddy.flight.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.challenge.travel_buddy.flight.services.model.Datum;
import com.challenge.travel_buddy.flight.services.repository.FlightSearchRepository;

import java.util.List;

import javax.inject.Inject;

public class AirportViewModel extends AndroidViewModel {

    private FlightSearchRepository flightSearchRepository;
    private LiveData<List<Datum>> searchStationModelLiveData;
//    private final MutableLiveData<String> projectID;

//    public ObservableField<> project = new ObservableField<>();

    @Inject
    public AirportViewModel(@NonNull FlightSearchRepository flightSearchRepository, @NonNull Application application) {
        super(application);
        this.flightSearchRepository = flightSearchRepository;
    }

    public LiveData<List<Datum>> searchAirport(String val){
        searchStationModelLiveData = flightSearchRepository.getAirportLists(val);
        return searchStationModelLiveData;
    }
}
