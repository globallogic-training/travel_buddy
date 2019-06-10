package com.challenge.travel_buddy.train.trainsearch.di;

import android.app.Application;

import com.challenge.travel_buddy.train.di.AppComponent;
import com.challenge.travel_buddy.train.trainsearch.view.ui.TrainSearch;

import dagger.Component;

@TrainSearchActivityScope
@Component( dependencies = AppComponent.class, modules = {TrainSearchActivityModule.class})

public interface TrainSearchActivityComponent {

    Application application();

    void inject(TrainSearch trainSearch);
}

