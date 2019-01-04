package com.example.admin.moviedbchallenge.di.application;

import android.arch.lifecycle.MutableLiveData;

import com.example.admin.moviedbchallenge.models.Result;

import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class LiveDataModule {

    @Provides
    public MutableLiveData<List<Result>> providesResultListMutableLiveData() {
        return new MutableLiveData<>();
    }
}
