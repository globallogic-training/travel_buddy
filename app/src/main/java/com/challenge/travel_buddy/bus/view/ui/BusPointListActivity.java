package com.challenge.travel_buddy.bus.view.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.challenge.travel_buddy.MVVMApplication;
import com.challenge.travel_buddy.R;
import com.challenge.travel_buddy.bus.di.BusPointActivityComponent;
import com.challenge.travel_buddy.bus.di.BusPointActivityScope;
import com.challenge.travel_buddy.bus.di.DaggerBusPointActivityComponent;
import com.challenge.travel_buddy.bus.services.model.BusPoint;
import com.challenge.travel_buddy.bus.viewmodal.BusPointViewModel;
import com.challenge.travel_buddy.train.trainsearch.di.TrainSearchActivityComponent;
import com.challenge.travel_buddy.train.trainsearch.services.model.Train;
import com.challenge.travel_buddy.train.trainsearch.viewmodal.TrainListViewModel;

import java.util.List;

import javax.inject.Inject;

public class BusPointListActivity extends AppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_list);


        BusPointActivityComponent busPointActivityComponent = DaggerBusPointActivityComponent.builder().appComponent(((MVVMApplication) getApplication()).getAppComponent()).build();
        busPointActivityComponent.inject(this);


        final BusPointViewModel viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(BusPointViewModel.class);

        viewModel.searchBusPoint("r").observe(this, new Observer<List<BusPoint>>() {
            @Override
            public void onChanged(List<BusPoint> busPoints) {
                
            }
        });

//        viewModel.getTrainList(getStationCode(fromStationCode), getStationCode(toStationCode), travelDate).observe(this, new Observer<Train>() {
//            @Override
//            public void onChanged(@Nullable Train train) {
//                fetchedTrains = train;
//                isTrainsAvailable = true;
//                setBothData();
//            }
//        });

//        viewModel.searchBusPoint()
//        viewModel.searchBusPoint()
    }
}
