package com.example.admin.moviedbchallenge.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.admin.moviedbchallenge.AppController;
import com.example.admin.moviedbchallenge.adapters.PopularMoviesAdapter;
import com.example.admin.moviedbchallenge.databinding.ActivityMainBinding;
import com.example.admin.moviedbchallenge.di.activity.ActivityModule;
import com.example.admin.moviedbchallenge.models.Result;
import com.example.admin.moviedbchallenge.utils.Constants;
import com.example.admin.moviedbchallenge.utils.EndlessRecyclerViewScrollListener;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements PopularMoviesAdapter.MyClickListener {

    private EndlessRecyclerViewScrollListener scrollListener;

    @Inject
    ActivityMainBinding binding;

    @Inject
    MainViewModel mainViewModel;

    @Inject
    GridLayoutManager layoutManager;

    @Inject
    DividerItemDecoration dividerItemDecoration;

    @Inject
    PopularMoviesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppController) getApplication()).getApplicationComponent()
                .newActivityComponent(new ActivityModule(this)).inject(this);
        configureRecyclerview();
        mainViewModel.getPopularMovies(1);
        mainViewModel.getPopularMoviesLiveData().observe(this, popularMovies -> {
            adapter.addMoviesToAdapter(popularMovies);
            adapter.notifyDataSetChanged();
        });
    }

    @Override
    public void itemClicked(Result result) {
        ItemFragment fragment = new ItemFragment();
        Bundle bundle = new Bundle();
        bundle.putString("movieTitle", result.getTitle());
        bundle.putString("movieReleaseDate", result.getReleaseDate());
        bundle.putString("movieOverview", result.getOverview());
        bundle.putString("movieBackDropPath", result.getBackdropPath());
        fragment.setArguments(bundle);
        fragment.show(getSupportFragmentManager(), Constants.BOTTOM_SHEET);
    }

    private void configureRecyclerview() {
        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                mainViewModel.getPopularMovies(page);
            }
        };
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.addItemDecoration(dividerItemDecoration);
        binding.recyclerView.addOnScrollListener(scrollListener);
        binding.recyclerView.setAdapter(adapter);
    }
}
