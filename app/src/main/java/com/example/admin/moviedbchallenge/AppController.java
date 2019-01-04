package com.example.admin.moviedbchallenge;

import android.app.Application;

import com.example.admin.moviedbchallenge.di.application.ApplicationComponent;
import com.example.admin.moviedbchallenge.di.application.DaggerApplicationComponent;

public class AppController extends Application {

    private ApplicationComponent applicationComponent;

    public ApplicationComponent getApplicationComponent() {
        if (applicationComponent == null) {
            applicationComponent = createApplicationComponent();
        }
        return applicationComponent;
    }

    private ApplicationComponent createApplicationComponent() {
        return DaggerApplicationComponent.create();
    }
}
