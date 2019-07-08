package com.challenge.travel_buddy;

import android.app.Application;

import com.challenge.travel_buddy.train.di.AppComponent;
import com.challenge.travel_buddy.train.di.AppModule;
import com.challenge.travel_buddy.train.di.DaggerAppComponent;

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
