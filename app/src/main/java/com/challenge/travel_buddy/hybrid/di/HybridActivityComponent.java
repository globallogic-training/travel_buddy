package com.challenge.travel_buddy.hybrid.di;

import android.app.Application;

import com.challenge.travel_buddy.hybrid.view.ui.SearchHybridActivity;
import com.challenge.travel_buddy.train.di.AppComponent;

import dagger.Component;

@HybridActivityScope
@Component (dependencies = AppComponent.class, modules = {HybridActivityModule.class})

public interface HybridActivityComponent {
    Application application();
    void Inject(SearchHybridActivity searchHybridActivity);
}
