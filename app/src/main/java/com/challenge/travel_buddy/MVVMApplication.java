package com.challenge.travel_buddy;

import android.app.Application;

import com.challenge.travel_buddy.di.AppComponent;
import com.challenge.travel_buddy.di.DaggerStationListActivityComponent;
import com.challenge.travel_buddy.di.StationListActivity;
import com.challenge.travel_buddy.di.StationListActivityComponent;

//import com.challenge.travel_buddy.di.DaggerAppComponent;

public class MVVMApplication extends Application {
    private StationListActivityComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerStationListActivityComponent.builder()
                .application(this)
                .build();
    }

    public StationListActivityComponent getAppComponent() {
        return mAppComponent;
    }
}
