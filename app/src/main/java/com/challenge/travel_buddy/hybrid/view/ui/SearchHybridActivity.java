package com.challenge.travel_buddy.hybrid.view.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.challenge.travel_buddy.MVVMApplication;
import com.challenge.travel_buddy.R;
import com.challenge.travel_buddy.bus.services.model.BusModel;
import com.challenge.travel_buddy.bus.viewmodal.BusListViewModel;
import com.challenge.travel_buddy.hybrid.di.DaggerHybridActivityComponent;
import com.challenge.travel_buddy.hybrid.di.HybridActivityComponent;
import com.challenge.travel_buddy.hybrid.di.HybridActivityScope;
import com.challenge.travel_buddy.hybrid.helper.HybridHelper;
import com.challenge.travel_buddy.hybrid.viewmodel.HybridViewModel;
import com.challenge.travel_buddy.train.trainsearch.view.ui.TrainSearch;
import com.challenge.travel_buddy.train.view.ui.StationListActivity;

import java.util.List;

import javax.inject.Inject;

public class SearchHybridActivity extends AppCompatActivity {

    private Button fromStationBtn;
    private Button toStationBtn;
    private static Button journeyDateValue;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_form);

        fromStationBtn = (Button) this.findViewById(R.id.fromStationValue1);
        toStationBtn = (Button) findViewById(R.id.toStationValue1);
        journeyDateValue = (Button) findViewById(R.id.journey_date_value1);

        HybridActivityComponent hybridActivityComponent = DaggerHybridActivityComponent.builder().appComponent(((MVVMApplication) getApplication()).getAppComponent()).build();
        hybridActivityComponent.Inject(this);
        final HybridViewModel viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(HybridViewModel.class);

        fromStationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HybridHelper.setStationIntent(SearchHybridActivity.this, true, StationListActivity.class);
            }
        });

        toStationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HybridHelper.setStationIntent(SearchHybridActivity.this, false, StationListActivity.class);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        if(data != null){
            super.onActivityResult(requestCode, resultCode, data);
            String fromStationText = data.getStringExtra("station");

            boolean isFrom = data.getBooleanExtra("isFrom", false);
            if(isFrom)
                fromStationBtn.setText(fromStationText);
            else
                toStationBtn.setText(fromStationText);
        }
    }
}
