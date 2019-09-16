package com.challenge.travel_buddy.flight.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.challenge.travel_buddy.MVVMApplication;
import com.challenge.travel_buddy.R;
import com.challenge.travel_buddy.bus.util.Utils;
import com.challenge.travel_buddy.flight.di.AirportActivityComponent;
import com.challenge.travel_buddy.flight.di.DaggerAirportActivityComponent;
import com.challenge.travel_buddy.flight.helper.FlightHelper;
import com.challenge.travel_buddy.flight.view.adapter.FlightListAdapter;
import com.challenge.travel_buddy.flight.viewmodel.FlightListViewModel;
import com.challenge.travel_buddy.train.trainsearch.view.ui.OnSwipeTouchListener;
import com.challenge.travel_buddy.train.trainsearch.view.ui.helper.Helper;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.Date;
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
    private ShimmerFrameLayout mShimmerFrameLayout;
    private LinearLayout noResultView;
    private LiveData<String> searchDate;
    private Button preDayBtn;
    private Button nxtDayBtn;
    private TextView travelDateTop;

    private TextView depTime;
    private TextView arrTime;
    private TextView fly_duration;
    private TextView flightCost;
    private TextView depFrom;
    private TextView arrTo;
    private TextView airlinesName;
    private TextView duration;
    private TextView journeyStop;
    private TextView seperatorOne;
    private TextView seperatortwo;
    private View topDivider;
    private TextView flightCostLbl;

    private LinearLayout bestFlightLoader;

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

        depFrom = findViewById(R.id.flight_bp);
        arrTo = findViewById(R.id.flight_dp);
        fly_duration = findViewById(R.id.duration);
        flightCost = findViewById(R.id.flight_cost);
        depTime = findViewById(R.id.flight_departure_time);
        arrTime = findViewById(R.id.flight_arrival_time);
        airlinesName = findViewById(R.id.airlinesName);
        duration = findViewById(R.id.duration);
        journeyStop = findViewById(R.id.journey_stop);
        seperatorOne = findViewById(R.id.separtor_1);
        seperatortwo = findViewById(R.id.separtor_2);
        topDivider = findViewById(R.id.topDivider);
        flightCostLbl = findViewById(R.id.flight_cost_lbl);

        flightListRecycler = findViewById(R.id.flightListRV);
        noResultView = findViewById(R.id.noResult_flight);
        preDayBtn = findViewById(R.id.prev_day_btn_flight);
        nxtDayBtn = findViewById(R.id.next_day_btn_flight);
        travelDateTop = findViewById(R.id.travel_date_top_flight);
        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, resId);
        flightListRecycler.setLayoutAnimation(animation);
        flightListRecycler.setLayoutManager(new LinearLayoutManager(this));


        mShimmerFrameLayout = findViewById(R.id.shimmer_view_container_flight);
        bestFlightLoader = findViewById(R.id.best_flight_loader);

        AirportActivityComponent airportActivityComponent = DaggerAirportActivityComponent.builder().appComponent(((MVVMApplication) getApplication()).getAppComponent()).build();
        airportActivityComponent.inject(this);


        FlightListViewModel viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(FlightListViewModel.class);

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
                        if(model != null){
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
                                    if(routesSize != 1){
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
            @Override
            public void onClick(View v) {
                String decrementedDate = FlightHelper.getFlightDateChanged(startDate, false);
                viewModel.setSearchDate(decrementedDate);
                performNextPrev(decrementedDate);
            }
        });

        flightListRecycler.setOnTouchListener(new OnSwipeTouchListener(this){
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                String increamentedDate = FlightHelper.getFlightDateChanged(startDate, true);
                viewModel.setSearchDate(increamentedDate);
                performNextPrev(increamentedDate);
                String dashedIncDate = (increamentedDate);
                Toast.makeText(FlightListActivity.this, "Results for : "+ dashedIncDate, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                Date date = Helper.getDateFromSlash(startDate);
                if(date.getTime() > System.currentTimeMillis()){
                    String decrementedDate = FlightHelper.getFlightDateChanged(startDate, false);
                    viewModel.setSearchDate(decrementedDate);
                    performNextPrev(decrementedDate);
                    String dashedDecrDate = Helper.getDashFromSlashDate(decrementedDate);
                    Toast.makeText(FlightListActivity.this, "Results for : "+ dashedDecrDate, Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(FlightListActivity.this, "Sorry!.. No flight results for past date.", Toast.LENGTH_SHORT).show();
                }
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

    private void setPrevBtnFunctionality(String dateOfSearch){
        Date date = Helper.getDateFromSlash(dateOfSearch);
        preDayBtn.setEnabled(date.getTime() > System.currentTimeMillis());
    }

    private void performNextPrev(String date){
        flightListRecycler.setVisibility(View.GONE);
        mShimmerFrameLayout.setVisibility(View.VISIBLE);
        mShimmerFrameLayout.startShimmerAnimation();
        setPrevBtnFunctionality(date);
    }

}
