package com.challenge.travel_buddy.bus.di;

import androidx.lifecycle.ViewModelProvider;

import com.challenge.travel_buddy.bus.services.repository.BusPointService;
import com.challenge.travel_buddy.bus.viewmodal.BusPointModelFactory;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(subcomponents = ViewModelSubComponent.class)
public class BusPointActivityModule {

    @BusPointActivityScope
    @Provides
    BusPointService provideBusPointService() {
        return new Retrofit.Builder().baseUrl("https://www.redbus.in")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BusPointService.class);
    }

    @BusPointActivityScope
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(
            ViewModelSubComponent.Builder viewModelSubComponent) {

        return new BusPointModelFactory(viewModelSubComponent.build());
    }
}
