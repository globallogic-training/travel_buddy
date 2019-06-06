package com.challenge.travel_buddy.di;

import android.app.Application;
import android.content.Context;
import android.media.AsyncPlayer;

import com.challenge.travel_buddy.view.ui.StationListActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
//    void inject(StationListActivity stationListActivity);
    Application application();
}