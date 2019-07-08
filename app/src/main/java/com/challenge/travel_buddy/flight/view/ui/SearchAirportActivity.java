package com.challenge.travel_buddy.flight.view.ui;

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
import com.challenge.travel_buddy.bus.view.ui.BusPointListActivity;
import com.challenge.travel_buddy.train.trainsearch.view.ui.TrainSearch;
import com.challenge.travel_buddy.train.view.ui.StationListActivity;
import com.challenge.travel_buddy.train.view.ui.fragment.DatePickerFragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Date;

public class SearchAirportActivity extends AppCompatActivity {

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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        getWebsite();
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
        Intent intent = new Intent(this, AirportListActivity.class);
        intent.putExtra("isFrom", isFrom);
        startActivityForResult(intent,2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(data != null){
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

    private void getWebsite() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();

                try {
                    Document doc = Jsoup.connect("https://www.goibibo.com/flights/air-IXR-DEL-20190629-20190702-1-0-0-E-D/").get();
                    String title = doc.title();
                    Elements links = doc.select(".fltPrice");

                    builder.append(title).append("\n");

                    for (Element link : links) {
                        builder.append("\n").append("Link : ").append(link.attr("class"))
                                .append("\n").append("Text : ").append(link.text());
                    }
                    System.out.println("String HTML"+builder.toString());
                } catch (IOException e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("jkj");
//                        result.setText(builder.toString());
                    }
                });
            }
        }).start();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
