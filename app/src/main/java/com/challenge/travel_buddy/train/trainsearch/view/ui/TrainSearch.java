package com.challenge.travel_buddy.train.trainsearch.view.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.challenge.travel_buddy.MVVMApplication;
import com.challenge.travel_buddy.R;
import com.challenge.travel_buddy.train.trainsearch.di.DaggerTrainSearchActivityComponent;
import com.challenge.travel_buddy.train.trainsearch.di.TrainSearchActivityComponent;
import com.challenge.travel_buddy.train.trainsearch.services.model.Available_Status;
import com.challenge.travel_buddy.train.trainsearch.services.model.Train;
import com.challenge.travel_buddy.train.trainsearch.services.model.TrainDataModel;
import com.challenge.travel_buddy.train.trainsearch.view.adapter.TrainListAdapter;
import com.challenge.travel_buddy.train.trainsearch.viewmodal.TrainListViewModel;

import java.util.ArrayList;
import java.util.Map;

import javax.inject.Inject;

public class TrainSearch extends AppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private String fromStationCode;
    private String toStationCode;
    private String travelDate;
    private String availDate;
    private TrainListAdapter adapter;
    private Train fetchedTrains;
    private Map <String, Map<String, Available_Status>> availableSeats;
    private RecyclerView trainListRecycler;
    private Boolean isSeatsAvilable;
    private Boolean isTrainsAvailable;
    ArrayList<TrainDataModel> serchedTrainList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_search);
        
        Intent intent = getIntent();
        fromStationCode = intent.getStringExtra("from");
        toStationCode = intent.getStringExtra("to");
        travelDate = intent.getStringExtra("date");
        availDate = intent.getStringExtra("avail_formated_date");
        trainListRecycler = findViewById(R.id.trainListRV);
        trainListRecycler.setLayoutManager(new LinearLayoutManager(this));
        isSeatsAvilable = false;
        isTrainsAvailable = false;
        
        TrainSearchActivityComponent trainSearchActivityComponent = DaggerTrainSearchActivityComponent.builder().appComponent(((MVVMApplication) getApplication()).getAppComponent()).build();
        trainSearchActivityComponent.inject(this);

        final TrainListViewModel viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(TrainListViewModel.class);

        viewModel.getTrainList(getStationCode(fromStationCode), getStationCode(toStationCode), travelDate).observe(this, new Observer<Train>() {
            @Override
            public void onChanged(@Nullable Train train) {
                fetchedTrains = train;
                isTrainsAvailable = true;
                setBothData();
            }
        });

        viewModel.getAvailability(getStationCode(fromStationCode), getStationCode(toStationCode), availDate)
                .observe(this, new Observer<Map <String, Map<String, Available_Status>>>() {
                    @Override
                    public void onChanged(@Nullable Map <String, Map<String, Available_Status>> availabilityModels) {
                        availableSeats = availabilityModels;
                        isSeatsAvilable = true;
                        setBothData();
                    }
                });

        System.out.println("In Activity");
    }
    private String getStationCode(String stationName){
        String stationCode = stationName.replaceAll("\\(|\\)","");
        String [] words = stationCode.split("\\s");
        if(words[1].contains("-")){
            if(stationName.contains("- All stations")){
                return words[0]+"-All";
            }else if(stationName.contains("- All")){
                return words[0]+"-All";
            }
        }else if(words.length > 2 && words[2].contains("-")){
            if(stationName.contains("- All stations")){
                return words[0]+" "+words[1]+"-All";
            }else if(stationName.contains("- All")){
                return words[0]+" "+words[1]+"-All";
            }
        }else if(stationName.contains("(")) {
            return words[words.length - 1];
        }

//        String regex = "\\s*\\bstations\\b\\s*";
//        String content = stationName.replaceAll(regex, "");
//        String regex1 = "\\s*\\b-\\b\\s*";
//        String content1 = content.replaceAll(regex1, "-");
        return stationCode;
    }

    public void setBothData() {
        if(isSeatsAvilable && isTrainsAvailable){
            for(TrainDataModel trainModel: fetchedTrains.getData().getTrainsBetweenStations()){
                String trainNumber = trainModel.getAttributes().getNumber();
                Map<String, Available_Status> avail = availableSeats.get(trainNumber);
                if(avail != null){
                    trainModel.setAvailable_seats(avail);
                    serchedTrainList.add(trainModel);
                }
            }
            fetchedTrains.getData().setTrainsBetweenStations(serchedTrainList);
            adapter = new TrainListAdapter(TrainSearch.this, fetchedTrains.getData().getTrainsBetweenStations());
            trainListRecycler.setAdapter(adapter);
            isSeatsAvilable = false;
            isTrainsAvailable = false;
        }
    }
}
