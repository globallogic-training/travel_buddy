package com.challenge.travel_buddy.bus.viewmodal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.challenge.travel_buddy.bus.services.model.BusPoint;
import com.challenge.travel_buddy.bus.services.repository.BusPointRepository;
import com.challenge.travel_buddy.train.services.model.SearchStationModel;
import com.challenge.travel_buddy.train.services.repository.StationRepository;

import java.util.List;

import javax.inject.Inject;

public class BusPointViewModel extends AndroidViewModel {

    private static final String TAG = com.challenge.travel_buddy.train.viewmodal.StationListViewModal.class.getName();

    private BusPointRepository busPointRepository;
    private LiveData<List<BusPoint>> searchStationModelLiveData;
//    private final MutableLiveData<String> projectID;

//    public ObservableField<> project = new ObservableField<>();

    @Inject
    public BusPointViewModel(@NonNull BusPointRepository busPointRepository, @NonNull Application application) {
        super(application);
        this.busPointRepository = busPointRepository;
    }

    public LiveData<List<BusPoint>>searchStation(String val){
        searchStationModelLiveData = busPointRepository.getBusPoint(val);
        return searchStationModelLiveData;
    }

}