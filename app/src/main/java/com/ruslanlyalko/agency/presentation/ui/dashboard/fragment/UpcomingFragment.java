package com.ruslanlyalko.agency.presentation.ui.dashboard.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ruslanlyalko.agency.R;
import com.ruslanlyalko.agency.data.models.Order;

import butterknife.ButterKnife;

/**
 * Created by Ruslan Lyalko
 * on 10.08.2018.
 */
public class UpcomingFragment extends Fragment {

    private Order mOrder;

    public static UpcomingFragment newInstance(Order order) {
        UpcomingFragment fragmentFirst = new UpcomingFragment();
        Bundle args = new Bundle();
        args.putParcelable("extra", order);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mOrder = getArguments() != null ? (Order) getArguments().getParcelable("extra") : new Order();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}