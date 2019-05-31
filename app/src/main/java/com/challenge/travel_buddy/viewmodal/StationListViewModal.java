package com.challenge.travel_buddy.viewmodal;

import android.app.Application;

import android.util.Log;


import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.challenge.travel_buddy.services.model.SearchStationModel;
import com.challenge.travel_buddy.services.repository.StationRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class StationListViewModal extends AndroidViewModel {

    private static final String TAG = StationListViewModal.class.getName();

    private StationRepository stationRepository;
    private LiveData<List<SearchStationModel>> searchStationModelLiveData;
//    private final MutableLiveData<String> projectID;

//    public ObservableField<> project = new ObservableField<>();

    @Inject
    public StationListViewModal(@NonNull StationRepository stationRepository, @NonNull Application application) {
        super(application);
        this.stationRepository = stationRepository;
    }

    public LiveData<List<SearchStationModel>>searchStation(String val){
        searchStationModelLiveData = stationRepository.getStation(val);
        return searchStationModelLiveData;
    }

}
