package com.example.admin.moviedbchallenge.factories;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.admin.moviedbchallenge.data.repository.Repository;
import com.example.admin.moviedbchallenge.ui.MainViewModel;

import javax.inject.Inject;

public class MainViewModelFactory implements ViewModelProvider.Factory {

    private Repository repository;

    @Inject
    public MainViewModelFactory(Repository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(repository);
        } else {
            throw new IllegalArgumentException("ViewModel not found");
        }
    }
}
