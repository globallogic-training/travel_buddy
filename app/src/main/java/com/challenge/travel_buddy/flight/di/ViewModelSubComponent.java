package com.challenge.travel_buddy.flight.di;

import com.challenge.travel_buddy.flight.viewmodel.AirportViewModel;
import com.challenge.travel_buddy.flight.viewmodel.FlightListViewModel;

import dagger.Subcomponent;

@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }
    AirportViewModel airportViewModel();
    FlightListViewModel flightListViewModel();

}