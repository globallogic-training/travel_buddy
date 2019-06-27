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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SearchActivity extends AppCompatActivity {

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

        fromStationValue1 = (Button) this.findViewById(R.id.fromStationValue1);
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
                callActivityForTrainSearch();
            }
        });

    }

    private void callActivityForTrainSearch() {
        Intent intent = new Intent(this, TrainSearch.class);
        intent.putExtra("from", fromStationValue1.getText());
        intent.putExtra("to", toStationValue1.getText());
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
            fromStationValue1.setText(fromStationText);
        else
            toStationValue1.setText(fromStationText);

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
