package com.challenge.travel_buddy.bus.di;

import dagger.Subcomponent;

@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }
//    TrainListViewModel trainListViewModal();

}
