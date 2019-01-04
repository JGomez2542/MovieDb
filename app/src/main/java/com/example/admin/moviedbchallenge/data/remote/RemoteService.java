package com.example.admin.moviedbchallenge.data.remote;

import com.example.admin.moviedbchallenge.models.MovieResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RemoteService {

    @GET("3/discover/movie")
    Single<MovieResponse> getPopularMovies(@Query("page") int page);
}
