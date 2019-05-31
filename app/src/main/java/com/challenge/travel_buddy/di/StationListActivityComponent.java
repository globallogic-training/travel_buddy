package com.challenge.travel_buddy.di;

import android.app.Application;

import com.challenge.travel_buddy.MVVMApplication;
import com.challenge.travel_buddy.view.ui.StationListActivity;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component( modules = {StationListActivityModule.class})

public interface StationListActivityComponent {
    void inject(StationListActivity stationListActivity);

    @Component.Builder
    interface Builder {
        StationListActivityComponent build();

        @BindsInstance
        Builder application(Application application);

    }
}
