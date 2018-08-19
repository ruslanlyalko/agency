package com.ruslanlyalko.agency.presentation.ui.dashboard.adapter.upcoming;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ruslanlyalko.agency.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ruslan Lyalko
 * on 10.08.2018.
 */
public class UpcomingPlaceholderFragment extends Fragment {

    @BindView(R.id.text_title) TextView mTextTitle;

    public static UpcomingPlaceholderFragment newInstance() {
        UpcomingPlaceholderFragment fragmentFirst = new UpcomingPlaceholderFragment();
        Bundle args = new Bundle();
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upcoming_placeholder, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTextTitle.setText(R.string.text_upcoming_placeholder);
    }
}