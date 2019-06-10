package com.challenge.travel_buddy.train.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
//    void inject(StationListActivity stationListActivity);
    Application application();
}