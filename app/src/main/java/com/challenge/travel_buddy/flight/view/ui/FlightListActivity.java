package com.challenge.travel_buddy.flight.view.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ProgressBar;

import com.challenge.travel_buddy.MVVMApplication;
import com.challenge.travel_buddy.R;
import com.challenge.travel_buddy.bus.view.adapter.BusListAdapter;
import com.challenge.travel_buddy.flight.di.AirportActivityComponent;
import com.challenge.travel_buddy.flight.di.DaggerAirportActivityComponent;
import com.challenge.travel_buddy.flight.services.model.Datum;
import com.challenge.travel_buddy.flight.services.model.Flight.FlightData;
import com.challenge.travel_buddy.flight.view.adapter.AirportListAdapter;
import com.challenge.travel_buddy.flight.view.adapter.FlightListAdapter;
import com.challenge.travel_buddy.flight.viewmodel.AirportViewModel;
import com.challenge.travel_buddy.flight.viewmodel.FlightListViewModel;

import java.util.List;

import javax.inject.Inject;

public class FlightListActivity extends AppCompatActivity {

    private String source;
    private String destination;
    private String startDate;
    private String endDate;
    private FlightListAdapter adapter;
    private RecyclerView flightListRecycler;
    private ProgressBar busProgressBar;
//    private ProgressBar bestBusProgressBar;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        source = intent.getStringExtra("from");
        destination = intent.getStringExtra("to");
        startDate = intent.getStringExtra("startDate");
        endDate = intent.getStringExtra("endDate");


        flightListRecycler = findViewById(R.id.flightListRV);

        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, resId);
        flightListRecycler.setLayoutAnimation(animation);
        flightListRecycler.setLayoutManager(new LinearLayoutManager(this));


        busProgressBar = findViewById(R.id.bus_list_progressBar);
//        bestBusProgressBar = findViewById(R.id.best_bus_progress);

        AirportActivityComponent airportActivityComponent = DaggerAirportActivityComponent.builder().appComponent(((MVVMApplication) getApplication()).getAppComponent()).build();
        airportActivityComponent.inject(this);


        FlightListViewModel viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(FlightListViewModel.class);

        viewModel.getFlight(trimForAiportCode(source), trimForAiportCode(destination), startDate, endDate).observe(this, new Observer<FlightData>() {
            @Override
            public void onChanged(@Nullable FlightData flightData) {
                if(flightData != null) {
                    adapter = new FlightListAdapter(FlightListActivity.this, flightData);
                    flightListRecycler.setAdapter(adapter);
                    busProgressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    private String trimForAiportCode(String str){
        String newstr = "";
        if (null != str && str.length() > 0 )
        {
            int endIndex = str.lastIndexOf("-") - 1;
            if (endIndex != -1)
            {
                newstr = str.substring(0, endIndex); // not forgot to put check if(endIndex != -1)
            }
        }
        return newstr;
    }

}