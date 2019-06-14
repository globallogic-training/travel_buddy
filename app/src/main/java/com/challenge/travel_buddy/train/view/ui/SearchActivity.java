package com.challenge.travel_buddy.train.view.ui;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.DialogFragment;

import com.challenge.travel_buddy.R;
import com.challenge.travel_buddy.train.trainsearch.view.ui.TrainSearch;
import com.challenge.travel_buddy.train.view.ui.fragment.DatePickerFragment;

public class SearchActivity extends AppCompatActivity {

    private TextView fromStationValue;
    private TextView toStationValue;
    private static TextView journeyDateValue;
    private static String date;
    private static String avail_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        LinearLayoutCompat fromStation = (LinearLayoutCompat) findViewById(R.id.source_layout);
        LinearLayoutCompat toStation = (LinearLayoutCompat) findViewById(R.id.destination_layout);
        journeyDateValue = (TextView) findViewById(R.id.journey_date_value);
        fromStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStationSearchListner(true);
            }
        });
        toStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setStationSearchListner(false);
            }
        });

        Button button = findViewById(R.id.search_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callActivityForTrainSearch();
            }
        });
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {search_btn
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    private void callActivityForTrainSearch() {
        Intent intent = new Intent(this, TrainSearch.class);
        intent.putExtra("from", fromStationValue.getText());
        intent.putExtra("to", toStationValue.getText());
        intent.putExtra("date", date);
        intent.putExtra("avail_formated_date", avail_date);
        startActivity(intent);
    }

    private void setStationSearchListner(boolean isFrom) {
        Intent intent = new Intent(this,StationListActivity.class);
        intent.putExtra("isFrom", isFrom);
        startActivityForResult(intent,2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fromStationValue = (TextView) findViewById(R.id.fromStationValue);
        toStationValue = (TextView) findViewById(R.id.toStationValue);
        String fromStationText = data.getStringExtra("station");
        boolean isFrom = data.getBooleanExtra("isFrom", false);
        if(isFrom)
            fromStationValue.setText(fromStationText);
        else
            toStationValue.setText(fromStationText);

    }
    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static void setJourneyDate(DatePicker view, int year, int month, int day){
        date = year+"-"+(month+1)+"-"+day;
        avail_date = day + "" + (month < 10 ? "0" + (month+1) : (month+1))+ "" + year;
        journeyDateValue.setText(day+"/"+(month+1)+"/"+year);
        System.out.println("New "+view+year+month+day );
    }

}
