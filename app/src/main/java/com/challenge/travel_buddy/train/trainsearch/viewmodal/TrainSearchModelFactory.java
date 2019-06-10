package com.challenge.travel_buddy.train.trainsearch.viewmodal;

import android.util.ArrayMap;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.challenge.travel_buddy.train.trainsearch.di.ViewModelSubComponent;

import java.util.Map;
import java.util.concurrent.Callable;

import javax.inject.Inject;

public class TrainSearchModelFactory implements ViewModelProvider.Factory{
    private final ArrayMap<Class, Callable<? extends ViewModel>> creators;

    @Inject
    public TrainSearchModelFactory(ViewModelSubComponent viewModelSubComponent) {
        creators = new ArrayMap<>();

        // View models cannot be injected directly because they won't be bound to the owner's view model scope.
        creators.put(TrainListViewModel.class, () -> viewModelSubComponent.trainListViewModal());
    }

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
