package com.challenge.travel_buddy.di;

import com.challenge.travel_buddy.viewmodal.StationListViewModal;

import dagger.Subcomponent;

//@StationListActivity
@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    StationListViewModal stationListViewModal();
}
