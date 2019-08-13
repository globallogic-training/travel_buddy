package com.challenge.travel_buddy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.challenge.travel_buddy.bus.view.ui.SearchBusActivity;
import com.challenge.travel_buddy.flight.view.ui.SearchAirportActivity;
import com.challenge.travel_buddy.train.view.ui.SearchActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent trainIntent = new Intent(this,SearchActivity.class);
        final Intent busIntent = new Intent(this, SearchBusActivity.class);
        final Intent flightIntent = new Intent(this, SearchAirportActivity.class);

        Button trainButton = (Button) findViewById(R.id.train_btn);
        trainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(trainIntent);
            }
        });

        Button busButton = (Button) findViewById(R.id.bus_btn);
        busButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(busIntent);
            }
        });
        Button flightButton = (Button) findViewById(R.id.flight_btn);
        flightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(flightIntent);
            }
        });

    }

}
