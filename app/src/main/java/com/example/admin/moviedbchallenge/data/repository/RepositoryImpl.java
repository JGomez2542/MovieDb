package com.example.admin.moviedbchallenge.data.repository;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.admin.moviedbchallenge.data.remote.RemoteServiceHelper;
import com.example.admin.moviedbchallenge.models.MovieResponse;
import com.example.admin.moviedbchallenge.models.Result;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RepositoryImpl implements Repository {

    private RemoteServiceHelper remoteServiceHelper;
    private MutableLiveData<List<Result>> popularMoviesLiveData;

    public RepositoryImpl(RemoteServiceHelper remoteServiceHelper, MutableLiveData<List<Result>> popularMoviesLiveData) {
        this.remoteServiceHelper = remoteServiceHelper;
        this.popularMoviesLiveData = popularMoviesLiveData;
    }

    @SuppressLint("CheckResult")
    @Override
    public void getPopularMovies(int pageNumber) {
        remoteServiceHelper.getPopularMovies(pageNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(MovieResponse::getResults)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(results -> popularMoviesLiveData.setValue(results), Throwable::printStackTrace);

    }

    @Override
    public LiveData<List<Result>> getPopularMoviesLiveData() {
        return popularMoviesLiveData;
    }
}
