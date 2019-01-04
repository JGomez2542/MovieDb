package com.example.admin.moviedbchallenge.di.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;

import com.example.admin.moviedbchallenge.R;
import com.example.admin.moviedbchallenge.adapters.PopularMoviesAdapter;
import com.example.admin.moviedbchallenge.factories.MainViewModelFactory;
import com.example.admin.moviedbchallenge.ui.MainViewModel;
import com.example.admin.moviedbchallenge.databinding.ActivityMainBinding;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    public MainViewModel providesMainViewModel(MainViewModelFactory factory) {
        return ViewModelProviders.of(activity, factory).get(MainViewModel.class);
    }

    @Provides
    @ActivityScope
    public ActivityMainBinding providesActivityMainBinding() {
        return DataBindingUtil.setContentView(activity, R.layout.activity_main);
    }

    @Provides
    public GridLayoutManager providesGridLayoutManager() {
        return new GridLayoutManager(activity, 2);
    }

    @Provides
    public DividerItemDecoration providesDividerItemDecoration() {
        return new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL);
    }

    @Provides
    public PopularMoviesAdapter providesPopularMoviesAdapter() {
        return new PopularMoviesAdapter(new ArrayList<>(), (PopularMoviesAdapter.MyClickListener) activity);
    }
}
