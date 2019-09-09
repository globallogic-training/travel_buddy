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
<<<<<<< Updated upstream
import android.widget.ProgressBar;

import com.challenge.travel_buddy.MVVMApplication;
import com.challenge.travel_buddy.R;
=======
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.challenge.travel_buddy.MVVMApplication;
import com.challenge.travel_buddy.R;
import com.challenge.travel_buddy.bus.util.Utils;
>>>>>>> Stashed changes
import com.challenge.travel_buddy.flight.di.AirportActivityComponent;
import com.challenge.travel_buddy.flight.di.DaggerAirportActivityComponent;
import com.challenge.travel_buddy.flight.view.adapter.FlightListAdapter;
import com.challenge.travel_buddy.flight.viewmodel.FlightListViewModel;

<<<<<<< Updated upstream
=======
import java.util.Date;
>>>>>>> Stashed changes
import java.util.List;
import java.util.Map;

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

<<<<<<< Updated upstream
//
        viewModel.getFlightData(trimForAiportCode(source), trimForAiportCode(destination), startDate, endDate).observe(FlightListActivity.this, new Observer<Map<String, Object>>() {
=======
        travelDateTop.setText(Helper.getDashFromSlashDate(startDate));
        viewModel.setSearchDate(startDate);

//        setPrevBtnFunctionality(availDate);
        searchDate = viewModel.getSearchDate();

        searchDate.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String changedDate) {
                viewModel.getFlightData(trimForAiportCode(source), trimForAiportCode(destination), changedDate, changedDate).observe(FlightListActivity.this, new Observer<Map<String, Object>>() {
                    @Override
                    public void onChanged(Map<String, Object> data) {
                        if(data != null) {
                            List<Map<String, Object>> flightList = fetchFlightList(data);
                            adapter = new FlightListAdapter(FlightListActivity.this, flightList);
                            flightListRecycler.setAdapter(adapter);
                            flightListRecycler.setVisibility(View.VISIBLE);
                            mShimmerFrameLayout.setVisibility(View.GONE);
                            noResultView.setVisibility(View.GONE);
                            startDate = changedDate;
                            travelDateTop.setText(Helper.getDashFromSlashDate(startDate));
                        }else{
                            flightListRecycler.setVisibility(View.GONE);
                            mShimmerFrameLayout.setVisibility(View.GONE);
                            noResultView.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        });
        viewModel.getCheapestFlightData(trimForAiportCode(source), trimForAiportCode(destination), startDate, startDate).observe(this
                , new Observer<Map<String, Object>>() {
                    @Override
                    public void onChanged(Map<String, Object> model) {
                        List<Map<String,String>> tempRoutes = (List)model.get("route");
                        depFrom.setText((String) model.get("cityFrom"));
                        arrTo.setText((String) model.get("cityTo"));
                        flightCost.setText(""+ ( (int) model.get("price")));
                        airlinesName.setText(FlightHelper.getAirlinesName((List<String>)model.get("airlines")));
                        duration.setText("Dur: "+(String) model.get("fly_duration"));
                        depTime.setText("Dep: "+ Utils.epochToString( ""+model.get("aTime")));
                        arrTime.setText("Arr: "+Utils.epochToString(""+model.get("dTime")));

                        int routesSize = tempRoutes.size();
                        StringBuilder resultantRoute = new StringBuilder();
                        for(int index = 0 ; index < routesSize; index++){
                            Map<String, String> routeTemp = tempRoutes.get(index);
                            if(index == 0){
                                resultantRoute.append(routeTemp.get("cityFrom"));
                            }
                            resultantRoute.append(" -> "+routeTemp.get("cityTo"));
                            if(index == (routesSize - 1)){
                                if(routesSize == 1) {
                                    resultantRoute = new StringBuilder("");

                                }else {
                                    resultantRoute.append(" " + (routesSize - 1) + " stop");
                                    journeyStop.setVisibility(View.VISIBLE);
                                }
                            }
                            journeyStop.setText(resultantRoute);
                        }

                        bestFlightLoader.setVisibility(View.GONE);
                        seperatorOne.setVisibility(View.VISIBLE);
                        seperatortwo.setVisibility(View.VISIBLE);
                        topDivider.setVisibility(View.VISIBLE);
                        flightCostLbl.setVisibility(View.VISIBLE);

                    }
                });
        nxtDayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String incBusTravelDate = FlightHelper.getFlightDateChanged(startDate, true);
                viewModel.setSearchDate(incBusTravelDate);
                performNextPrev(incBusTravelDate);
            }
        });

        preDayBtn.setOnClickListener(new View.OnClickListener() {
>>>>>>> Stashed changes
            @Override
            public void onChanged(Map<String, Object> data) {
                List<Map<String,Object>> flightList = fetchFlightList(data);
                adapter = new FlightListAdapter(FlightListActivity.this, flightList);
                flightListRecycler.setAdapter(adapter);
                busProgressBar.setVisibility(View.GONE);
            }
        });
    }
    private List<Map<String, Object>> fetchFlightList(Map<String, Object> data){
            List<Map<String, Object>> flight = (List) data.get("data");
        return flight;
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
