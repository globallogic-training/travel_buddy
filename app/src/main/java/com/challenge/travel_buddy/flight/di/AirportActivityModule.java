package com.challenge.travel_buddy.flight.di;

import androidx.lifecycle.ViewModelProvider;

import com.challenge.travel_buddy.flight.services.repository.AirportService;
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
        return new Retrofit.Builder().baseUrl("https://www.ixigo.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AirportService.class);
    }

    @AirportActivityScope
    @Provides
    ViewModelProvider.Factory provideAirportModelFactory(
            ViewModelSubComponent.Builder viewModelSubComponent) {

        return new AiportModelFactory(viewModelSubComponent.build());
    }
}
