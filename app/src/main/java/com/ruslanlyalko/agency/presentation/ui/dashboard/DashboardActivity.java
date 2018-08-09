package com.ruslanlyalko.agency.presentation.ui.dashboard;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ruslanlyalko.agency.R;
import com.ruslanlyalko.agency.data.models.Order;
import com.ruslanlyalko.agency.data.models.User;
import com.ruslanlyalko.agency.presentation.base.BaseActivity;
import com.ruslanlyalko.agency.presentation.ui.dashboard.adapter.OnItemClickListener;
import com.ruslanlyalko.agency.presentation.ui.dashboard.adapter.PastOrdersAdapter;

import java.util.List;

import butterknife.BindView;

public class DashboardActivity extends BaseActivity<DashboardPresenter> implements DashboardView, OnItemClickListener {

    @BindView(R.id.text_add_more) TextView mTextAddMore;
    @BindView(R.id.text_balance) TextView mTextBalance;
    @BindView(R.id.text_income) TextView mTextIncome;
    @BindView(R.id.text_expense) TextView mTextExpense;
    @BindView(R.id.recycler_past_orders) RecyclerView mRecyclerPastOrders;
    private PastOrdersAdapter mAdapter = new PastOrdersAdapter(this);

    public static Intent getLaunchIntent(final BaseActivity activity) {
        return new Intent(activity, DashboardActivity.class);
    }

    @Override
    public void showUser(final MutableLiveData<User> user) {
        user.observe(this, new Observer<User>() {
            @Override
            public void onChanged(@Nullable final User user) {
//                if (user != null)
//                    mTextView.setText(user.getName());
            }
        });
    }

    @Override
    public void setPastOrders(final List<Order> list) {
        mAdapter.setData(list);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_dashboard;
    }

    @Override
    protected void initPresenter(final Intent intent) {
        setPresenter(new DashboardPresenter());
    }

    @Override
    protected void onViewReady(final Bundle savedInstanceState) {
        mRecyclerPastOrders.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerPastOrders.setAdapter(mAdapter);
        getPresenter().fetchPastOrders();
    }

    @Override
    public void onItemClicked(final View view, final int position) {
        Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show();
    }
}