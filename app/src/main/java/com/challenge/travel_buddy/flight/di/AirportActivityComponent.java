package com.challenge.travel_buddy.flight.di;

import android.app.Application;

import com.challenge.travel_buddy.flight.view.ui.AirportListActivity;
import com.challenge.travel_buddy.flight.view.ui.FlightListActivity;
import com.challenge.travel_buddy.train.di.AppComponent;

import dagger.Component;

@AirportActivityScope
@Component( dependencies = AppComponent.class, modules = {AirportActivityModule.class})

public interface AirportActivityComponent {

    Application application();

    void inject(AirportListActivity airportListActivity);
    void inject(FlightListActivity flightListActivity);
}