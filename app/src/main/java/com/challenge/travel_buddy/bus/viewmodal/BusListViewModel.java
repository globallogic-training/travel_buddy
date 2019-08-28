package com.challenge.travel_buddy.bus.viewmodal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.challenge.travel_buddy.bus.services.model.busresponse.BusSearchResponse;
import com.challenge.travel_buddy.bus.services.model.busresponse.Inv;
import com.challenge.travel_buddy.bus.services.repository.BusPointRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class BusListViewModel extends AndroidViewModel {

    private BusPointRepository busPointRepository;
    private MutableLiveData<List<Inv>> busSearchResult;
    private LiveData<Map<Date, List<Inv>>> fututeBuses;
    private MutableLiveData<String> searchDate;


    @Inject
    public BusListViewModel(@NonNull BusPointRepository busPointRepository, @NonNull Application application) {
        super(application);
        this.busPointRepository = busPointRepository;
        searchDate = new MutableLiveData<>();
    }

    public LiveData<List<Inv>> getAllBuses(String from, String to, String fromStationId, String toStationId, String DOJ){
        busSearchResult =  busPointRepository.getAllBuses(from, to, fromStationId, toStationId,DOJ);
        return busSearchResult;

    }

    public LiveData<Map<Date, List<Inv>>> getFutureBuses(String from, String to, String fromStationId, String toStationId, String DOJ){
        fututeBuses = busPointRepository.getFutureBuses(from, to, fromStationId, toStationId,DOJ);
        return fututeBuses;
    }

    public void setSerchDate(String newDate){
        searchDate.setValue(newDate);
    }

    public MutableLiveData<String> getSearchDate(){
        return searchDate;
    }
}
