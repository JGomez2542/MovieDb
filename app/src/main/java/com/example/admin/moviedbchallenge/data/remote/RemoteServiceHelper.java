package com.example.admin.moviedbchallenge.data.remote;

import com.example.admin.moviedbchallenge.models.MovieResponse;
import com.example.admin.moviedbchallenge.utils.Constants;
import com.example.admin.moviedbchallenge.utils.MyInterceptor;

import javax.inject.Inject;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteServiceHelper {

    @Inject
    public RemoteServiceHelper() {
    }

    public Single<MovieResponse> getPopularMovies(int pageNumber) {
        Retrofit retrofit = getRetrofit();
        RemoteService service = retrofit.create(RemoteService.class);
        return service.getPopularMovies(pageNumber);
    }

    private Retrofit getRetrofit() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        MyInterceptor myInterceptor = new MyInterceptor();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(myInterceptor)
                .build();
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
