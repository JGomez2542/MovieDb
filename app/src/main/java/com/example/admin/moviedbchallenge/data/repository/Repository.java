package com.example.admin.moviedbchallenge.data.repository;

import android.arch.lifecycle.LiveData;

import com.example.admin.moviedbchallenge.models.Result;

import java.util.List;

public interface Repository {

    void getPopularMovies(int pageNumber);

    LiveData<List<Result>> getPopularMoviesLiveData();
}
