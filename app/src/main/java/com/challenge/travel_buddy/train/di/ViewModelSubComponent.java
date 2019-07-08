package com.challenge.travel_buddy.train.di;

import com.challenge.travel_buddy.train.viewmodal.StationListViewModal;

import dagger.Subcomponent;

@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }
    StationListViewModal stationListViewModal();

}
