package com.challenge.travel_buddy.train.di;

import android.app.Application;

import com.challenge.travel_buddy.train.view.ui.StationListActivity;

import dagger.Component;

//@Singleton
@StationActivityScope
@Component( dependencies = AppComponent.class, modules = {StationListActivityModule.class})

public interface StationListActivityComponent {

    Application application();

    void inject(StationListActivity stationListActivity);
}
