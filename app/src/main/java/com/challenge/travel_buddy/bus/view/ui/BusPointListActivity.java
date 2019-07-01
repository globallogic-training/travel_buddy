package com.challenge.travel_buddy.bus.view.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.challenge.travel_buddy.MVVMApplication;
import com.challenge.travel_buddy.R;
import com.challenge.travel_buddy.bus.di.BusPointActivityComponent;
import com.challenge.travel_buddy.bus.di.DaggerBusPointActivityComponent;
import com.challenge.travel_buddy.bus.services.model.BusModel;
import com.challenge.travel_buddy.bus.view.adapter.BusStationAdapter;
import com.challenge.travel_buddy.bus.viewmodal.BusPointViewModel;

import java.util.List;

import javax.inject.Inject;

public class BusPointListActivity extends AppCompatActivity {

    BusStationAdapter adapter;
    RecyclerView stationRecyclerView;
    boolean isFrom;
    protected BusPointListActivity instance = this;

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
        editText.requestFocus();
        if(isFrom)
            editText.setHint("Source");
        else
            editText.setHint("Destination");


        BusPointActivityComponent busPointActivityComponent = DaggerBusPointActivityComponent.builder().appComponent(((MVVMApplication) getApplication()).getAppComponent()).build();
        busPointActivityComponent.inject(this);


        final BusPointViewModel viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(BusPointViewModel.class);



        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final StringBuilder sb = new StringBuilder(s.length());
                sb.append(s);
                viewModel.searchBusPoint(sb.toString()).observe(instance, new Observer<List<BusModel>>() {
                    @Override
                    public void onChanged(@Nullable List<BusModel> project) {
                        if(project != null) {
                            adapter = new BusStationAdapter(BusPointListActivity.this, project, isFrom);
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
