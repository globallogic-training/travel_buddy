package com.challenge.travel_buddy;

import android.app.Application;

//import com.challenge.travel_buddy.di.DaggerStationListActivityComponent;
import com.challenge.travel_buddy.di.AppComponent;
import com.challenge.travel_buddy.di.AppModule;
import com.challenge.travel_buddy.di.DaggerAppComponent;
import com.challenge.travel_buddy.di.StationListActivityComponent;

//import com.challenge.travel_buddy.di.DaggerAppComponent;

public class MVVMApplication extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
