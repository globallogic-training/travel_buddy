package com.challenge.travel_buddy.bus.di;

import android.app.Application;

import com.challenge.travel_buddy.bus.view.ui.BusPointListActivity;
import com.challenge.travel_buddy.bus.view.ui.BusResultActivity;
import com.challenge.travel_buddy.train.di.AppComponent;
import com.challenge.travel_buddy.train.trainsearch.di.TrainSearchActivityScope;

import dagger.Component;

@BusPointActivityScope
@Component( dependencies = AppComponent.class, modules = {BusPointActivityModule.class})

public interface BusPointActivityComponent {

    Application application();

    void inject(BusPointListActivity busPointListActivity);
    void inject(BusResultActivity busResultActivity);
}