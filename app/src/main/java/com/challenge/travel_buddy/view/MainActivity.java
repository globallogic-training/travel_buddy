package com.challenge.travel_buddy.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.challenge.travel_buddy.R;

public class MainActivity extends AppCompatActivity {

    Button flight, hydrid, bus, train;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flight = findViewById(R.id.flight_btn);
        hydrid = findViewById(R.id.hybrid_btn);
        bus = findViewById(R.id.bus_btn);
        train = findViewById(R.id.train_btn);

        flight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("flight","flight clicked");
            }
        });

        hydrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("hybrid","hybrid clicked");
            }
        });

        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("bus","bus clicked");
            }
        });

        train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("train","train clicked");
            }
        });

    }
}
