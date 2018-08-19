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
import com.ruslanlyalko.agency.data.models.Order;
import com.ruslanlyalko.agency.presentation.ui.dashboard.adapter.past.OnItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Ruslan Lyalko
 * on 10.08.2018.
 */
public class UpcomingFragment extends Fragment {

    private static final String EXTRA_ORDER = "order";
    private static final String EXTRA_POSITION = "position";
    private static OnItemClickListener mOnItemClickListener;
    @BindView(R.id.text_title) TextView mTextTitle;
    @BindView(R.id.text_subtitle) TextView mTextSubtitle;
    private int mPosition;
    private Order mOrder;

    public static UpcomingFragment newInstance(Order order, int position, final OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
        UpcomingFragment fragmentFirst = new UpcomingFragment();
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_ORDER, order);
        args.putInt(EXTRA_POSITION, position);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mOrder = getArguments() != null ? (Order) getArguments().getParcelable(EXTRA_ORDER) : new Order();
        mPosition = getArguments() != null ? getArguments().getInt(EXTRA_POSITION) : 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTextTitle.setText(mOrder.getClientNamePhone());
        mTextSubtitle.setText(mOrder.getDescription());
    }

    @OnClick(R.id.card)
    public void onClick(View v) {
        if (mOnItemClickListener != null)
            mOnItemClickListener.onItemClicked(v, mPosition);
    }
}