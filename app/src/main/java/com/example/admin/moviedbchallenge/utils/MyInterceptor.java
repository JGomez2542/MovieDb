package com.example.admin.moviedbchallenge.utils;

import com.example.admin.moviedbchallenge.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MyInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();
        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("language", "en")
                .addQueryParameter("sort_by", "popularity.desc")
                .addQueryParameter("api_key", BuildConfig.API_KEY)
                .build();
        Request request = original.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}
