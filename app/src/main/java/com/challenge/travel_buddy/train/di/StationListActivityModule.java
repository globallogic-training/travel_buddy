package com.challenge.travel_buddy.train.di;


import androidx.lifecycle.ViewModelProvider;

import com.challenge.travel_buddy.train.services.repository.StationService;
import com.challenge.travel_buddy.train.viewmodal.StationListViewModelFactory;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(subcomponents = ViewModelSubComponent.class)
public class StationListActivityModule {
    @StationActivityScope
    @Provides
    StationService provideStationService() {
        return new Retrofit.Builder().baseUrl("https://www.ixigo.com/action/content/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(StationService.class);
    }

    @StationActivityScope
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(
            ViewModelSubComponent.Builder viewModelSubComponent) {

        return new StationListViewModelFactory(viewModelSubComponent.build());
    }
}
