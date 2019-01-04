package com.example.admin.moviedbchallenge.adapters;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.admin.moviedbchallenge.R;
import com.example.admin.moviedbchallenge.databinding.RecyclerItemBinding;
import com.example.admin.moviedbchallenge.models.Result;
import com.example.admin.moviedbchallenge.utils.Constants;

import java.util.List;

public class PopularMoviesAdapter extends RecyclerView.Adapter<PopularMoviesAdapter.ViewHolder> {

    private List<Result> popularMovies;
    private MyClickListener clickListener;

    public PopularMoviesAdapter(List<Result> popularMovies, MyClickListener clickListener) {
        this.popularMovies = popularMovies;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public PopularMoviesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.recycler_item, viewGroup, false);
        return new ViewHolder(binding);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull PopularMoviesAdapter.ViewHolder holder, int i) {
        Result temp = popularMovies.get(i);
        holder.itemView.setOnClickListener(view -> clickListener.itemClicked(popularMovies.get(i)));
        CircularProgressDrawable progressDrawable = new CircularProgressDrawable(holder.itemView.getContext());
        progressDrawable.setStrokeWidth(5f);
        progressDrawable.setCenterRadius(30f);
        progressDrawable.start();
        RequestOptions options = new RequestOptions();
        options.centerCrop();
        options.placeholder(progressDrawable);
        Glide.with(holder.itemView.getContext())
                .asBitmap()
                .load(Constants.POSTER_PATH + temp.getPosterPath())
                .apply(options)
                .into(holder.binding.movieImage);
    }

    @Override
    public int getItemCount() {
        return popularMovies.size();
    }

    public void addMoviesToAdapter(List<Result> newResults) {
        popularMovies.addAll(newResults);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerItemBinding binding;

        public ViewHolder(@NonNull RecyclerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface MyClickListener {
        void itemClicked(Result result);
    }
}
