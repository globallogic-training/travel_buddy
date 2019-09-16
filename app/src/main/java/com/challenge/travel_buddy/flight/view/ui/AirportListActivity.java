package com.challenge.travel_buddy.flight.view.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.challenge.travel_buddy.MVVMApplication;
import com.challenge.travel_buddy.R;

import com.challenge.travel_buddy.flight.di.AirportActivityComponent;
import com.challenge.travel_buddy.flight.di.DaggerAirportActivityComponent;
import com.challenge.travel_buddy.flight.services.model.Airport;
import com.challenge.travel_buddy.flight.view.adapter.AirportListAdapter;
import com.challenge.travel_buddy.flight.viewmodel.AirportViewModel;


import java.util.List;

import javax.inject.Inject;

public class AirportListActivity extends AppCompatActivity {

    AirportListAdapter adapter;
    RecyclerView stationRecyclerView;
    boolean isFrom;
    protected AirportListActivity instance = this;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        stationRecyclerView.addItemDecoration(decoration);
        stationRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        EditText editText = findViewById(R.id.station_edittext);
        editText.requestFocus();
        if(isFrom)
            editText.setHint("Source");
        else
            editText.setHint("Destination");


        AirportActivityComponent airportActivityComponent = DaggerAirportActivityComponent.builder().appComponent(((MVVMApplication) getApplication()).getAppComponent()).build();
        airportActivityComponent.inject(this);


        final AirportViewModel viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(AirportViewModel.class);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final StringBuilder sb = new StringBuilder(s.length());
                sb.append(s);
                viewModel.searchAirport(sb.toString()).observe(instance, new Observer<List<Airport>>() {
                    @Override
                    public void onChanged(@Nullable List<Airport> airport) {
                        if(airport != null) {
                            adapter = new AirportListAdapter(AirportListActivity.this, airport, isFrom);
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
