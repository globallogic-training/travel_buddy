package com.challenge.travel_buddy.bus.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.challenge.travel_buddy.R;

public class BusResultActivity extends AppCompatActivity {

    private String fromStationCode;
    private String toStationCode;
    private String travelDate;
    private String availDate;
    private RecyclerView busListRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_search);

        Intent intent = getIntent();
        fromStationCode = intent.getStringExtra("from");
        toStationCode = intent.getStringExtra("to");
        travelDate = intent.getStringExtra("date");
        availDate = intent.getStringExtra("avail_formated_date");
        busListRecycler = findViewById(R.id.trainListRV);
        busListRecycler.setLayoutManager(new LinearLayoutManager(this));




    }
}
