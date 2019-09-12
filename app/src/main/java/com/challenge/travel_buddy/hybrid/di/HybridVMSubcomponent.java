package com.challenge.travel_buddy.hybrid.di;

import com.challenge.travel_buddy.hybrid.viewmodel.HybridViewModel;

import dagger.Subcomponent;

@Subcomponent
public interface HybridVMSubcomponent {
    @Subcomponent.Builder
    interface Builder {
        HybridVMSubcomponent build();
    }

    HybridViewModel hybridViewModel();
}
