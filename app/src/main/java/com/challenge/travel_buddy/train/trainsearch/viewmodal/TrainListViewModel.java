package com.challenge.travel_buddy.train.trainsearch.viewmodal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.challenge.travel_buddy.train.trainsearch.services.model.Available_Status;
import com.challenge.travel_buddy.train.trainsearch.services.model.Train;
import com.challenge.travel_buddy.train.trainsearch.services.model.TrainAvailabilityModel;
import com.challenge.travel_buddy.train.trainsearch.services.model.Train_Number_Search;
import com.challenge.travel_buddy.train.trainsearch.services.repository.TrainSearchRepository;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class TrainListViewModel extends AndroidViewModel {

    private static final String TAG = TrainListViewModel.class.getName();

    private TrainSearchRepository trainSearchRepository;
    private MutableLiveData<Train> trainListModelLiveData;
    private MutableLiveData<Map <String, Map<String, Available_Status>>> seatAvailability;
    private MutableLiveData<List<TrainAvailabilityModel>> bestAvailInMonth1;
    private MutableLiveData<Train_Number_Search> trainNameFromCodeSL;
    private MutableLiveData<Train_Number_Search> trainNameFromCode3A;


    @Inject
    public TrainListViewModel(@NonNull TrainSearchRepository trainSearchRepository, @NonNull Application application) {
        super(application);
        this.trainSearchRepository = trainSearchRepository;
    }

    public LiveData<Train> getTrainList(String from, String to, String date) {
        if (trainListModelLiveData == null){
            trainListModelLiveData = trainSearchRepository.getTrains(from, to, date);
        }
        return trainListModelLiveData;
    }

    public LiveData<Map <String, Map <String,Available_Status>>> getAvailability(String from, String to, String date){
        if(seatAvailability == null){
            seatAvailability = trainSearchRepository.getAvailability(from, to, date);
        }
        return seatAvailability;
    }

    public MutableLiveData<List<TrainAvailabilityModel>> getBestTrainInMonth(String from, String to, String date) {
        if(bestAvailInMonth1 == null)
            bestAvailInMonth1 = trainSearchRepository.getBestTrainInMonth(from,to,date);
        return bestAvailInMonth1;
    }

    public MutableLiveData<Train_Number_Search> getTrainFromCodeSL(String trainNumber){
        if(trainNameFromCodeSL == null)
            trainNameFromCodeSL = trainSearchRepository.getTrainFromCode(trainNumber);
        return trainNameFromCodeSL;
    }

    public MutableLiveData<Train_Number_Search> getTrainFromCode3A(String trainNumber){
        if(trainNameFromCode3A == null)
            trainNameFromCode3A = trainSearchRepository.getTrainFromCode(trainNumber);
        return trainNameFromCode3A;
    }
}