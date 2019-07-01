package com.challenge.travel_buddy.flight.di;

import com.challenge.travel_buddy.bus.viewmodal.BusPointViewModel;
import com.challenge.travel_buddy.flight.services.model.Airport;
import com.challenge.travel_buddy.flight.viewmodel.AirportViewModel;

import dagger.Subcomponent;

@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }
    AirportViewModel airportViewModel();

}