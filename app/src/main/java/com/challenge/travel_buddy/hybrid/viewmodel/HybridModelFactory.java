package com.challenge.travel_buddy.hybrid.viewmodel;

import android.util.ArrayMap;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.challenge.travel_buddy.bus.viewmodal.BusPointViewModel;
import com.challenge.travel_buddy.hybrid.di.HybridActivityScope;
import com.challenge.travel_buddy.hybrid.di.HybridVMSubcomponent;

import java.util.Map;
import java.util.concurrent.Callable;

import javax.inject.Inject;

@HybridActivityScope
public class HybridModelFactory implements ViewModelProvider.Factory {
    private final ArrayMap<Class, Callable<? extends ViewModel>> creators;

    @Inject
    public HybridModelFactory(HybridVMSubcomponent hybridVMSubcomponent){
        creators = new ArrayMap<>();
        creators.put(HybridViewModel.class, () -> hybridVMSubcomponent.hybridViewModel());
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        Callable<? extends ViewModel> creator = creators.get(modelClass);
        if (creator == null) {
            for (Map.Entry<Class, Callable<? extends ViewModel>> entry : creators.entrySet()) {
                if (modelClass.isAssignableFrom(entry.getKey())) {
                    creator = entry.getValue();
                    break;
                }
            }
        }
        if (creator == null) {
            throw new IllegalArgumentException("Unknown model class " + modelClass);
        }
        try {
            return (T) creator.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
