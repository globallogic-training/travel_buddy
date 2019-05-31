package com.challenge.travel_buddy.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.challenge.travel_buddy.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent intent = new Intent(this,SearchActivity.class);

        Button flightButton = (Button) findViewById(R.id.flight_btn);
        flightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });



//        ActivityComponent component = DaggerActivityComponent.builder()
//                .horsePower(120)
//                .engineCapacity(1400)
//                .appComponent(((ExampleApp) getApplication()).getAppComponent())
//                .build();

//        component.inject(this);
//
//        car1.drive();
//        car2.drive();
    }
}
