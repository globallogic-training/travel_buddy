package com.challenge.travel_buddy.train.trainsearch.di;

import androidx.lifecycle.ViewModelProvider;

import com.challenge.travel_buddy.train.trainsearch.services.repository.TrainSearchService;
import com.challenge.travel_buddy.train.trainsearch.viewmodal.TrainSearchModelFactory;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(subcomponents = ViewModelSubComponent.class)
public class TrainSearchActivityModule {

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
