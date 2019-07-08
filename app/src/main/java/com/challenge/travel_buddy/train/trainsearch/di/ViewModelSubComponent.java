package com.challenge.travel_buddy.train.trainsearch.di;

import com.challenge.travel_buddy.train.trainsearch.viewmodal.TrainListViewModel;

import dagger.Subcomponent;

@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }
    TrainListViewModel trainListViewModal();

}
