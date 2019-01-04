package com.example.admin.moviedbchallenge.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.admin.moviedbchallenge.R;
import com.example.admin.moviedbchallenge.databinding.FragmentItemBinding;
import com.example.admin.moviedbchallenge.utils.Constants;

public class ItemFragment extends BottomSheetDialogFragment {

    private FragmentItemBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setOnShowListener(dialog -> {
            BottomSheetDialog d = (BottomSheetDialog) dialog;
            CoordinatorLayout coordinatorLayout = d.findViewById(R.id.coordinatorLayout);
            LinearLayout bottomSheetInternal = d.findViewById(R.id.movieInfo);
            BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetInternal);
            BottomSheetBehavior.from((View) coordinatorLayout.getParent()).setPeekHeight(bottomSheetInternal.getHeight());
            bottomSheetBehavior.setPeekHeight(bottomSheetInternal.getHeight());
            coordinatorLayout.getParent().requestLayout();
        });
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_item, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        binding.movieTitle.setText(bundle.getString("movieTitle"));
        binding.movieReleaseDate.setText(String.format(getContext().getString(R.string.release_date),
                bundle.getString("movieReleaseDate")));
        binding.movieOverview.setText(bundle.getString("movieOverview"));
        CircularProgressDrawable progressDrawable = new CircularProgressDrawable(getActivity());
        progressDrawable.setStrokeWidth(5f);
        progressDrawable.setCenterRadius(30f);
        progressDrawable.start();
        RequestOptions options = new RequestOptions();
        options.placeholder(progressDrawable);
        options.centerCrop();
        Glide.with(getActivity())
                .asBitmap()
                .load(Constants.BACKDROP_PATH + bundle.getString("movieBackDropPath"))
                .apply(options)
                .into(binding.movieImage);
    }
}
