package com.challenge.travel_buddy.bus.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.challenge.travel_buddy.MVVMApplication;
import com.challenge.travel_buddy.R;
import com.challenge.travel_buddy.bus.di.BusPointActivityComponent;
import com.challenge.travel_buddy.bus.di.DaggerBusPointActivityComponent;
import com.challenge.travel_buddy.bus.services.model.BusModel;
import com.challenge.travel_buddy.bus.services.model.busresponse.BusSearchResponse;
import com.challenge.travel_buddy.bus.services.model.busresponse.Inv;
import com.challenge.travel_buddy.bus.view.adapter.BusListAdapter;
import com.challenge.travel_buddy.bus.viewmodal.BusListViewModel;
import com.challenge.travel_buddy.bus.viewmodal.BusPointViewModel;
import com.challenge.travel_buddy.train.trainsearch.di.DaggerTrainSearchActivityComponent;
import com.challenge.travel_buddy.train.trainsearch.di.TrainSearchActivityComponent;

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
    private Inv invObj;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_search);

        Intent intent = getIntent();
        fromStation = intent.getStringExtra("from");
        toStation = intent.getStringExtra("to");
        travelDate = formatDate(intent.getStringExtra("date"));
        availDate = intent.getStringExtra("avail_formated_date");
        fromStationId = intent.getStringExtra("fromStationId");
        toStationId = intent.getStringExtra("toStationId");
        busListRecycler = findViewById(R.id.trainListRV);
        busListRecycler.setLayoutManager(new LinearLayoutManager(this));
        bus_list_progressBar = findViewById(R.id.train_list_progressBar);

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
        invObj = superBusList.get(keys.iterator().next());
        for(Date date : superBusList.keySet()){

            List<Inv> buses = superBusList.get(date);
            Inv bestBus = getBestBus(buses);

            if(bestBus != null && invObj != null ){
                invObj = ( invObj.getMinfr() < bestBus.getMinfr() ) ? invObj : bestBus;
            }
        }

        System.out.println("new");
    }

    private Inv getBestBus(List<Inv> buses) {
        Inv invObjDay = buses.get(0) ;
        for(Inv bus : buses){
            if(invObjDay != null && bus != null){
              invObjDay = invObjDay.getMinfr() < bus.getMinfr() ? invObjDay : bus;
            }
        }
        return invObjDay;
    }


}
