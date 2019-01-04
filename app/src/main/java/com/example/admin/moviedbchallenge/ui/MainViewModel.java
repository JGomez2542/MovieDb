package com.example.admin.moviedbchallenge.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.admin.moviedbchallenge.data.repository.Repository;
import com.example.admin.moviedbchallenge.models.Result;

import java.util.List;

public class MainViewModel extends ViewModel {

    private Repository repository;

    public MainViewModel(Repository repository) {
        this.repository = repository;
    }

    public void getPopularMovies(int pageNumber) {
        repository.getPopularMovies(pageNumber);
    }

    public LiveData<List<Result>> getPopularMoviesLiveData() {
        return repository.getPopularMoviesLiveData();
    }
}
