package com.challenge.travel_buddy.view.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.challenge.travel_buddy.MVVMApplication;
import com.challenge.travel_buddy.R;


import com.challenge.travel_buddy.di.AppComponent;
import com.challenge.travel_buddy.di.AppModule;
import com.challenge.travel_buddy.di.DaggerAppComponent;
import com.challenge.travel_buddy.di.DaggerStationListActivityComponent;
import com.challenge.travel_buddy.di.StationActivityScope;
import com.challenge.travel_buddy.di.StationListActivityComponent;
import com.challenge.travel_buddy.services.model.SearchStationModel;
import com.challenge.travel_buddy.view.adapter.StationListAdapter;
import com.challenge.travel_buddy.viewmodal.StationListViewModal;

import java.util.List;

import javax.inject.Inject;

public class StationListActivity extends AppCompatActivity {
    StationListAdapter adapter;
    RecyclerView stationRecyclerView;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_list);
        stationRecyclerView = (RecyclerView) findViewById(R.id.stationRecylerView);
        stationRecyclerView.setLayoutManager(new LinearLayoutManager(this));

//        AppComponent component = DaggerAppComponent.builder().build();


//        component.inject(this);

//        AppComponent appComponent = DaggerAppComponent.builder().appModule(new AppModule(this.getApplication())).build();

        StationListActivityComponent stationListActivityComponent = DaggerStationListActivityComponent.builder().appComponent(((MVVMApplication) getApplication()).getAppComponent()).build();
        stationListActivityComponent.inject(this);
//        StationListActivityComponent component = DaggerStationListActivityComponent.builder()
//                .application(getApplication())
//                .build();
//
//        ((StationListActivityComponent) component).inject(this);

        final StationListViewModal viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(StationListViewModal.class);

        viewModel.searchStation("ngp").observe(this, new Observer<List<SearchStationModel>>() {
            @Override
            public void onChanged(@Nullable List<SearchStationModel> project) {
                adapter = new StationListAdapter(StationListActivity.this, project);
                stationRecyclerView.setAdapter(adapter);
//               System.out.println("lop");
            }
        });

    }
}
