package com.challenge.travel_buddy.train.trainsearch.viewmodal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.challenge.travel_buddy.train.trainsearch.services.model.Available_Status;
import com.challenge.travel_buddy.train.trainsearch.services.model.Train;
import com.challenge.travel_buddy.train.trainsearch.services.repository.TrainSearchRepository;

import java.util.Map;

import javax.inject.Inject;

public class TrainListViewModel extends AndroidViewModel {

    private static final String TAG = TrainListViewModel.class.getName();

    private TrainSearchRepository trainSearchRepository;
    private LiveData<Train> trainListModelLiveData;
    private LiveData<Map <String, Map<String, Available_Status>>> seatAvailability;
//    private final MutableLiveData<String> projectID;

//    public ObservableField<> project = new ObservableField<>();

    @Inject
    public TrainListViewModel(@NonNull TrainSearchRepository trainSearchRepository, @NonNull Application application) {
        super(application);
        this.trainSearchRepository = trainSearchRepository;
    }

    public LiveData<Train>getTrainList(String from, String to, String date){
        trainListModelLiveData = trainSearchRepository.getTrains(from, to, date);
        return trainListModelLiveData;
    }

    public LiveData<Map <String, Map <String,Available_Status>>> getAvailability(String from, String to, String date){
        seatAvailability = trainSearchRepository.getAvailability(from, to, date);
        return seatAvailability;
    }

}