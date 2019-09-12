package com.challenge.travel_buddy.hybrid.di;

import androidx.lifecycle.ViewModelProvider;

import com.challenge.travel_buddy.bus.services.repository.BusPointService;
import com.challenge.travel_buddy.hybrid.viewmodel.HybridModelFactory;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(subcomponents = HybridVMSubcomponent.class)
public class HybridActivityModule {

    @HybridActivityScope
    @Provides
       BusPointService provideBusPointService() {
           return new Retrofit.Builder().baseUrl("https://www.redbus.in")
                   .addConverterFactory(GsonConverterFactory.create())
                   .build()
                   .create(BusPointService.class);
       }

    @HybridActivityScope
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(
            HybridVMSubcomponent.Builder hybridVMSubcomponent ){
        return new HybridModelFactory(hybridVMSubcomponent.build());
    }

}
