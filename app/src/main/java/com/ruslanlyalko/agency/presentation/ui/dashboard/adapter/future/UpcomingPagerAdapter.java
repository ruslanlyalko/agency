package com.ruslanlyalko.agency.presentation.ui.dashboard.adapter.future;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ruslanlyalko.agency.data.models.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ruslan Lyalko
 * on 10.08.2018.
 */
public class UpcomingPagerAdapter extends FragmentPagerAdapter {

    private List<Order> mData = new ArrayList<>();

    public UpcomingPagerAdapter(final FragmentManager fm) {
        super(fm);
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
    public Fragment getItem(final int i) {
        return UpcomingFragment.newInstance(mData.get(i));
    }
}
