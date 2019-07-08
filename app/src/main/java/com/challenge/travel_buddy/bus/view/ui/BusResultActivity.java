package com.challenge.travel_buddy.bus.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.challenge.travel_buddy.MVVMApplication;
import com.challenge.travel_buddy.R;
import com.challenge.travel_buddy.bus.di.BusPointActivityComponent;
import com.challenge.travel_buddy.bus.di.DaggerBusPointActivityComponent;
import com.challenge.travel_buddy.bus.services.model.busresponse.Inv;
import com.challenge.travel_buddy.bus.util.Utils;
import com.challenge.travel_buddy.bus.view.adapter.BusListAdapter;
import com.challenge.travel_buddy.bus.viewmodal.BusListViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

public class BusResultActivity extends AppCompatActivity {

    private String fromStation;
    private String toStation;
    private String travelDate;
    private String availDate;
    private String fromStationId;
    private String toStationId;
    private BusListAdapter adapter;
    private RecyclerView busListRecycler;
    private ProgressBar bus_list_progressBar;
    private ProgressBar best_bus_progress;
    private Inv invObj;
    private TextView busName;
    private TextView busType;
    private TextView total_bus_seats;
    private TextView total_window_seats;
    private TextView bus_bp;
    private TextView bus_dp;
    private TextView busArrivalTime;
    private TextView busDepartureTime;
    private TextView bus_travel_hrs;
    private TextView bus_cost;
    private TextView busRating;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        fromStation = intent.getStringExtra("from");
        toStation = intent.getStringExtra("to");
        travelDate = formatDate(intent.getStringExtra("date"));
        availDate = intent.getStringExtra("avail_formated_date");
        fromStationId = intent.getStringExtra("fromStationId");
        toStationId = intent.getStringExtra("toStationId");
        busListRecycler = findViewById(R.id.busListRV);

        int resId = R.anim.layout_animation_fall_down;
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this, resId);
        busListRecycler.setLayoutAnimation(animation);
        busListRecycler.setLayoutManager(new LinearLayoutManager(this));


        bus_list_progressBar = findViewById(R.id.bus_list_progressBar);
        best_bus_progress = findViewById(R.id.best_bus_progress);

        busName = findViewById(R.id.busName);
        busType = findViewById(R.id.busType);
        total_bus_seats = findViewById(R.id.total_bus_seats);
        total_window_seats = findViewById(R.id.total_window_seats);
        bus_bp = findViewById(R.id.bus_bp);
        bus_dp = findViewById(R.id.bus_dp);
        busArrivalTime = findViewById(R.id.bus_arrival_time);
        busDepartureTime = findViewById(R.id.bus_departure_time);
        bus_travel_hrs = findViewById(R.id.bus_travel_hrs);
        bus_cost = findViewById(R.id.bus_cost);
        busRating = findViewById(R.id.busRating);

        BusPointActivityComponent busPointActivityComponent = DaggerBusPointActivityComponent.builder().appComponent(((MVVMApplication) getApplication()).getAppComponent()).build();
        busPointActivityComponent.inject(this);

        final BusListViewModel viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(BusListViewModel.class);

        viewModel.getAllBuses(fromStation,toStation,fromStationId,toStationId,travelDate)
                .observe(this, new Observer<List<Inv>>() {
                    @Override
                    public void onChanged(List<Inv> invs) {
                        adapter = new BusListAdapter(BusResultActivity.this, invs);
                        busListRecycler.setAdapter(adapter);
                        bus_list_progressBar.setVisibility(View.GONE);
                    }
                });

        viewModel.getFutureBuses(fromStation,toStation,fromStationId,toStationId,travelDate)
                .observe(this, new Observer<Map<Date, List<Inv>>>() {
                    @Override
                    public void onChanged(Map<Date, List<Inv>> superBusList) {
                        getOptimizedResult(superBusList);
                    }
                });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public String formatDate(String date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-dd");
        SimpleDateFormat formatter1 = new SimpleDateFormat("d-MMM-yyyy");
        String formatedDate = "";
        try {
            Date date1 = formatter.parse(date);
            formatedDate = formatter1.format(date1);
            Log.d("formated date", formatedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatedDate;
    }

    void getOptimizedResult(Map<Date, List<Inv>> superBusList){
        List<Inv> invObjList = new ArrayList<>();
        Set<Date> keys = superBusList.keySet();
        invObj = superBusList.get(keys.iterator().next()).get(0);
        for(Date date : superBusList.keySet()){

            List<Inv> buses = superBusList.get(date);
            Inv bestBus = getBestBus(buses);

            if(bestBus != null && invObj != null ){
                invObj = ( invObj.getMinfr() < bestBus.getMinfr() ) ? invObj : bestBus;
            }
        }

        busName.setText(invObj.getTvs());
        busType.setText(invObj.getBt());
        total_bus_seats.setText(invObj.getNsa().toString());
        total_window_seats.setText(invObj.getWnSt().toString());
//        bus_bp.setText(invObj.getStdBp());
//        bus_dp.setText(invObj.getStdDp());
        bus_cost.setText("₹ "+invObj.getMinfr().toString());
//        busArrivalTime.setText("Arrv Date: "+Utils.formatArrDepTimeWithDate(invObj.getAt()));
        busDepartureTime.setText("Date: "+Utils.formatArrDepTimeWithDate(invObj.getDt()));
        bus_travel_hrs.setText(Utils.converMinsToHrs(invObj.getDuration()));
        if(invObj.getRt().getTotRt() != null)
            busRating.setText(  Math.floor(invObj.getRt().getTotRt() * 10) / 10 +"\u2605");

        best_bus_progress.setVisibility(View.GONE);

    }

    private Inv getBestBus(List<Inv> buses) {
        Inv invObjDay = buses.get(0) ;
        for(Inv bus : buses){
            int isHighRate = Double.compare(bus.getRt().getTotRt(), 4d);
            if(invObjDay != null && bus != null && isHighRate > 0  ){
              invObjDay = invObjDay.getMinfr() < bus.getMinfr() ? invObjDay : bus;
            }
        }
        return invObjDay;
    }

}
