package com.challenge.travel_buddy.view.ui;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.challenge.travel_buddy.R;

public class SearchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        TextView fromStation = (TextView) findViewById(R.id.fromStation);
        TextView toStation = (TextView) findViewById(R.id.toStation);

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

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    private void setStationSearchListner(boolean isFrom) {
        Intent intent = new Intent(this,StationListActivity.class);
        intent.putExtra("isFrom", isFrom);
        startActivity(intent);
    }

}
