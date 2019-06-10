package com.challenge.travel_buddy.train.view.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.challenge.travel_buddy.MVVMApplication;
import com.challenge.travel_buddy.R;


import com.challenge.travel_buddy.train.di.DaggerStationListActivityComponent;
import com.challenge.travel_buddy.train.di.StationListActivityComponent;
import com.challenge.travel_buddy.train.services.model.SearchStationModel;
import com.challenge.travel_buddy.train.view.adapter.StationListAdapter;
import com.challenge.travel_buddy.train.viewmodal.StationListViewModal;

import java.util.List;

import javax.inject.Inject;

public class StationListActivity extends AppCompatActivity {
    StationListAdapter adapter;
    RecyclerView stationRecyclerView;
    boolean isFrom;
    protected StationListActivity instance = this;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_list);


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                isFrom = false;
            } else {
                isFrom = extras.getBoolean("isFrom");
            }
        } else {
            isFrom = (Boolean) savedInstanceState.getBoolean("STRING_I_NEED");
        }

        stationRecyclerView = (RecyclerView) findViewById(R.id.stationRecylerView);
        stationRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        EditText editText = findViewById(R.id.station_edittext);
        if(isFrom)
            editText.setHint("Source");
        else
            editText.setHint("Destination");
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

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final StringBuilder sb = new StringBuilder(s.length());
                sb.append(s);
                viewModel.searchStation(sb.toString()).observe(instance, new Observer<List<SearchStationModel>>() {
                    @Override
                    public void onChanged(@Nullable List<SearchStationModel> project) {
                        if(project != null) {
                            adapter = new StationListAdapter(StationListActivity.this, project, isFrom);
                            stationRecyclerView.setAdapter(adapter);
                        }
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




    }
}