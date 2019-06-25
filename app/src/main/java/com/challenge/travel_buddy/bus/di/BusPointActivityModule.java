package com.challenge.travel_buddy.bus.di;

import androidx.lifecycle.ViewModelProvider;

import com.challenge.travel_buddy.train.trainsearch.di.TrainSearchActivityScope;
import com.challenge.travel_buddy.train.trainsearch.di.ViewModelSubComponent;
import com.challenge.travel_buddy.train.trainsearch.services.repository.TrainSearchService;
import com.challenge.travel_buddy.train.trainsearch.viewmodal.TrainSearchModelFactory;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(subcomponents = com.challenge.travel_buddy.train.trainsearch.di.ViewModelSubComponent.class)
public class BusPointActivityModule {

    @TrainSearchActivityScope
    @Provides
    TrainSearchService provideTrainSearchService() {
        return new Retrofit.Builder().baseUrl("https://www.ixigo.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TrainSearchService.class);
    }

    @TrainSearchActivityScope
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(
            ViewModelSubComponent.Builder viewModelSubComponent) {

        return new TrainSearchModelFactory(viewModelSubComponent.build());
    }
}
