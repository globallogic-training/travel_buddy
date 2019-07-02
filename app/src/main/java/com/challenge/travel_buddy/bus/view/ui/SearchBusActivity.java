package com.challenge.travel_buddy.bus.view.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.challenge.travel_buddy.R;
import com.challenge.travel_buddy.train.trainsearch.view.ui.TrainSearch;
import com.challenge.travel_buddy.train.view.ui.StationListActivity;
import com.challenge.travel_buddy.train.view.ui.fragment.DatePickerFragment;

import java.util.Date;

public class SearchBusActivity extends AppCompatActivity {

    private TextView fromStationValue;
    private TextView toStationValue;
    private static TextView journeyDateValue;

    private Button fromStationValue1;
    private Button toStationValue1;
    private static Button journeyDateValue1;

    private static String date;
    private static String avail_date;
    private String fromStationId;
    private String toStationId;

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
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callActivityForBusSearch();
            }
        });
    }
    private void setStationSearchListner(boolean isFrom) {
        Intent intent = new Intent(this, BusPointListActivity.class);
        intent.putExtra("isFrom", isFrom);
        startActivityForResult(intent,2);
    }

    private void callActivityForBusSearch() {
        Intent intent = new Intent(this, BusResultActivity.class);
        intent.putExtra("from", fromStationValue1.getText());
        intent.putExtra("to", toStationValue1.getText());
        intent.putExtra("date", date);
        intent.putExtra("avail_formated_date", avail_date);
        intent.putExtra("fromStationId", fromStationId);
        intent.putExtra("toStationId", toStationId);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fromStationValue = (TextView) findViewById(R.id.fromStationValue);
        toStationValue = (TextView) findViewById(R.id.toStationValue);
        String fromStationText = data.getStringExtra("station");
        boolean isFrom = data.getBooleanExtra("isFrom", false);
        if(isFrom){
            fromStationValue1.setText(fromStationText);
            fromStationId = data.getStringExtra("stationId");
        }
        else{
            toStationValue1.setText(fromStationText);
            toStationId = data.getStringExtra("stationId");
        }

    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static void setJourneyDate(DatePicker view, int year, int month, int day){
        Date date1;
        date = year+"-"+(month+1)+"-"+day;
        avail_date = (day < 10 ? "0" + (day) : (day)) + "" + (month < 10 ? "0" + (month+1) : (month+1))+ "" + year;
        journeyDateValue1.setText(day+"/"+(month+1)+"/"+year);
        System.out.println("New "+view+year+month+day );
    }
}
