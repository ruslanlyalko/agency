package com.ruslanlyalko.agency.presentation.ui.dashboard.adapter.upcoming;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ruslanlyalko.agency.data.models.Order;
import com.ruslanlyalko.agency.presentation.ui.dashboard.adapter.past.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ruslan Lyalko
 * on 10.08.2018.
 */
public class UpcomingPagerAdapter extends FragmentStatePagerAdapter {

    private final OnItemClickListener mOnItemClickListener;
    private List<Order> mData = new ArrayList<>();

    public UpcomingPagerAdapter(final FragmentManager fm, OnItemClickListener onItemClickListener) {
        super(fm);
        mOnItemClickListener = onItemClickListener;
    }

    public void setData(final List<Order> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(final int i) {
        return UpcomingFragment.newInstance(mData.get(i), i, mOnItemClickListener);
    }

    public List<Order> getData() {
        return mData;
    }
}
