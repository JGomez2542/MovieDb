package com.example.admin.moviedbchallenge.di.application;

import android.arch.lifecycle.MutableLiveData;

import com.example.admin.moviedbchallenge.data.remote.RemoteServiceHelper;
import com.example.admin.moviedbchallenge.data.repository.Repository;
import com.example.admin.moviedbchallenge.data.repository.RepositoryImpl;
import com.example.admin.moviedbchallenge.models.Result;

import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    @Provides
    @ApplicationScope
    public Repository providesRepository(RemoteServiceHelper remoteServiceHelper, MutableLiveData<List<Result>> liveData) {
        return new RepositoryImpl(remoteServiceHelper, liveData);
    }
}
