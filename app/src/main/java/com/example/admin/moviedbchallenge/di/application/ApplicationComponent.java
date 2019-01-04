package com.example.admin.moviedbchallenge.di.application;

import com.example.admin.moviedbchallenge.data.remote.RemoteServiceHelper;
import com.example.admin.moviedbchallenge.di.activity.ActivityComponent;
import com.example.admin.moviedbchallenge.di.activity.ActivityModule;

import dagger.Component;

@ApplicationScope
@Component(modules = {ApplicationModule.class, LiveDataModule.class})
public interface ApplicationComponent {

    ActivityComponent newActivityComponent(ActivityModule activityModule);

    RemoteServiceHelper getRemoteServiceHelper();
}
