package com.challenge.travel_buddy.di;

import android.app.Application;

import com.challenge.travel_buddy.services.repository.StationService;
import com.challenge.travel_buddy.viewmodal.StationListViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    private Application mApplication;

    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }

//    @Singleton
//    @Provides
//    StationService provideStationService() {
//        return new Retrofit.Builder().baseUrl("https://www.ixigo.com/action/content/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(StationService.class);
//    }
//
//    @Singleton
//    @Provides
//    ViewModelProvider.Factory provideViewModelFactory(
//            ViewModelSubComponent.Builder viewModelSubComponent) {
//
//        return new StationListViewModelFactory(viewModelSubComponent.build());
//    }

}
