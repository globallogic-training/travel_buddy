package com.challenge.travel_buddy.bus.di;

import com.challenge.travel_buddy.bus.viewmodal.BusListViewModel;
import com.challenge.travel_buddy.bus.viewmodal.BusPointViewModel;

import dagger.Subcomponent;

@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }
    BusPointViewModel busPointViewModel();
    BusListViewModel busListViewModel();

}
