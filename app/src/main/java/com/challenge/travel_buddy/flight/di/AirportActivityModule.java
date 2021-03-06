package com.challenge.travel_buddy.flight.di;

import androidx.lifecycle.ViewModelProvider;

import com.challenge.travel_buddy.flight.services.repository.AirportService;
import com.challenge.travel_buddy.flight.services.repository.FlightService;
import com.challenge.travel_buddy.flight.viewmodel.AiportModelFactory;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(subcomponents = ViewModelSubComponent.class)
public class AirportActivityModule {

    @AirportActivityScope
    @Provides
    AirportService provideAirportService() {
        return new Retrofit.Builder().baseUrl("https://voyager.goibibo.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AirportService.class);
    }

    @AirportActivityScope
    @Provides
    FlightService provideFlightService() {
        return new Retrofit.Builder().baseUrl("https://api.skypicker.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FlightService.class);
    }


    @AirportActivityScope
    @Provides
    ViewModelProvider.Factory provideAirportModelFactory(
            ViewModelSubComponent.Builder viewModelSubComponent) {

        return new AiportModelFactory(viewModelSubComponent.build());
    }
}
