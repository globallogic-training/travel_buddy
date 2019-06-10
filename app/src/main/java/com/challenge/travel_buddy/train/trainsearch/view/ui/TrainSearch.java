package com.challenge.travel_buddy.train.trainsearch.view.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import com.challenge.travel_buddy.MVVMApplication;
import com.challenge.travel_buddy.R;
import com.challenge.travel_buddy.train.trainsearch.di.DaggerTrainSearchActivityComponent;
import com.challenge.travel_buddy.train.trainsearch.di.TrainSearchActivityComponent;
import com.challenge.travel_buddy.train.trainsearch.services.model.Train;
import com.challenge.travel_buddy.train.trainsearch.viewmodal.TrainListViewModel;

import javax.inject.Inject;

public class TrainSearch extends AppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private String fromStationCode;
    private String toStationCode;
    private String travelDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_search);
        
        Intent intent = getIntent();
        fromStationCode = intent.getStringExtra("from");
        toStationCode = intent.getStringExtra("to");
        travelDate = intent.getStringExtra("date");
        
        TrainSearchActivityComponent trainSearchActivityComponent = DaggerTrainSearchActivityComponent.builder().appComponent(((MVVMApplication) getApplication()).getAppComponent()).build();
        trainSearchActivityComponent.inject(this);

        final TrainListViewModel viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(TrainListViewModel.class);
        viewModel.getTrainList(getStationCode(fromStationCode), getStationCode(toStationCode), travelDate).observe(this, new Observer<Train>() {
            @Override
            public void onChanged(@Nullable Train train) {
//                if(project != null) {
//                    adapter = new StationListAdapter(StationListActivity.this, project, isFrom);
//                    stationRecyclerView.setAdapter(adapter);
//                }
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
}
