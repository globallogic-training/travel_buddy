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
import androidx.recyclerview.widget.DividerItemDecoration;
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

    private StationListAdapter adapter;
    private RecyclerView stationRecyclerView;
    private boolean isFrom;
    private StationListActivity instance = this;
    private EditText stationSearchEditText;
    private StationListViewModal viewModel ;
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

        stationSearchEditText = findViewById(R.id.station_edittext);
        stationSearchEditText.requestFocus();
        if(isFrom)
            stationSearchEditText.setHint("Source");
        else
            stationSearchEditText.setHint("Destination");

        //AppComponent component = DaggerAppComponent.builder().build();
        //component.inject(this);
        //AppComponent appComponent = DaggerAppComponent.builder().appModule(new AppModule(this.getApplication())).build();

        StationListActivityComponent stationListActivityComponent = DaggerStationListActivityComponent.builder().appComponent(((MVVMApplication) getApplication()).getAppComponent()).build();
        stationListActivityComponent.inject(this);

        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(StationListViewModal.class);

        searchTextListener();

    }

    private void searchTextListener(){
        stationSearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                final StringBuilder sb = new StringBuilder(s.length());
                sb.append(s);
                viewModel.searchStation(sb.toString()).observe(instance, new Observer<List<SearchStationModel>>() {
                    @Override
                    public void onChanged(@Nullable List<SearchStationModel> stations) {
                        if(stations != null) {
                            adapter = new StationListAdapter(StationListActivity.this, stations, isFrom);
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
