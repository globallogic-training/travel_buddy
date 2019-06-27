package com.challenge.travel_buddy.bus.view.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.challenge.travel_buddy.R;
import com.challenge.travel_buddy.train.view.ui.StationListActivity;

public class SearchBusActivity extends AppCompatActivity {

    private TextView fromStationValue;
    private TextView toStationValue;
    private static TextView journeyDateValue;

    private Button fromStationValue1;
    private Button toStationValue1;
    private static Button journeyDateValue1;

    private static String date;
    private static String avail_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_form);


        fromStationValue1 = (Button) findViewById(R.id.fromStationValue1);
        toStationValue1 = (Button) findViewById(R.id.toStationValue1);
        journeyDateValue1 = (Button) findViewById(R.id.journey_date_value1);

        fromStationValue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStationSearchListner(true);
            }
        });
        toStationValue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStationSearchListner(false);
            }
        });

        Button button = findViewById(R.id.search_btn1);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                callActivityForTrainSearch();
//            }
//        });
    }
    private void setStationSearchListner(boolean isFrom) {
        Intent intent = new Intent(this, BusPointListActivity.class);
        intent.putExtra("isFrom", isFrom);
        startActivityForResult(intent,2);
    }
}
