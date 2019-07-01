package com.challenge.travel_buddy.bus.viewmodal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.challenge.travel_buddy.bus.services.model.BusModel;
import com.challenge.travel_buddy.bus.services.repository.BusPointRepository;

import java.util.List;

import javax.inject.Inject;

public class BusPointViewModel extends AndroidViewModel {

    private static final String TAG = com.challenge.travel_buddy.train.viewmodal.StationListViewModal.class.getName();

    private BusPointRepository busPointRepository;
    private LiveData<List<BusModel>> searchStationModelLiveData;
//    private final MutableLiveData<String> projectID;

//    public ObservableField<> project = new ObservableField<>();

    @Inject
    public BusPointViewModel(@NonNull BusPointRepository busPointRepository, @NonNull Application application) {
        super(application);
        this.busPointRepository = busPointRepository;
    }

    public LiveData<List<BusModel>>searchBusPoint(String val){
        searchStationModelLiveData = busPointRepository.getBusPoint(val);
        return searchStationModelLiveData;
    }

}