package com.example.admin.moviedbchallenge.di.activity;

import com.example.admin.moviedbchallenge.factories.MainViewModelFactory;
import com.example.admin.moviedbchallenge.ui.MainActivity;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    MainViewModelFactory getMainViewModelFactory();
}
