package com.ruslanlyalko.agency.presentation.ui.dashboard;

import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ruslanlyalko.agency.R;
import com.ruslanlyalko.agency.data.models.Order;
import com.ruslanlyalko.agency.data.models.User;
import com.ruslanlyalko.agency.presentation.base.BaseActivity;
import com.ruslanlyalko.agency.presentation.ui.dashboard.adapter.future.UpcomingPagerAdapter;
import com.ruslanlyalko.agency.presentation.ui.dashboard.adapter.past.OnItemClickListener;
import com.ruslanlyalko.agency.presentation.ui.dashboard.adapter.past.PastOrdersAdapter;
import com.ruslanlyalko.agency.presentation.utils.DateUtils;
import com.ruslanlyalko.agency.presentation.utils.RxView;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.OnClick;

public class DashboardActivity extends BaseActivity<DashboardPresenter> implements DashboardView, OnItemClickListener {

    @BindView(R.id.text_add_more) TextView mTextAddMore;
    @BindView(R.id.text_balance) TextView mTextBalance;
    @BindView(R.id.text_income) TextView mTextIncome;
    @BindView(R.id.text_expense) TextView mTextExpense;
    @BindView(R.id.recycler_past_orders) RecyclerView mRecyclerPastOrders;
    @BindView(R.id.bottom_sheet) CardView mBottomSheet;
    @BindView(R.id.view_pager_upcoming) ViewPager mViewPagerUpcoming;
    // Add order
    @BindView(R.id.image_client_avatar) ImageView mImageClientAvatar;
    @BindView(R.id.edit_phone) EditText mEditPhone;
    @BindView(R.id.edit_name) EditText mEditName;
    @BindView(R.id.text_date) TextView mTextDate;
    @BindView(R.id.text_time) TextView mTextTime;
    @BindView(R.id.text_hrs) TextView mTextDuration;
    @BindView(R.id.edit_income) EditText mEditIncome;
    @BindView(R.id.edit_expense) EditText mEditExpense;
    @BindView(R.id.edit_description) EditText mEditDescription;
    @BindView(R.id.check_aqua) CheckBox mCheckAqua;
    @BindView(R.id.check_mk) CheckBox mCheckMk;
    @BindView(R.id.check_pinata) CheckBox mCheckPinata;
    @BindView(R.id.text_equipment) TextView mTextEquipment;
    @BindView(R.id.text_children_count) TextView mTextChildrenCount;
    // Dimens
    @BindDimen(R.dimen.margin_default) int mMargin16;
    @BindDimen(R.dimen.margin_double) int mMargin32;

    private Order mNewOrder = new Order();
    private BottomSheetBehavior mSheetBehavior;
    private PastOrdersAdapter mPastAdapter = new PastOrdersAdapter(this);
    private UpcomingPagerAdapter mUpcomingAdapter;

    public static Intent getLaunchIntent(final BaseActivity activity) {
        return new Intent(activity, DashboardActivity.class);
    }

    @Override
    public void showUser(final MutableLiveData<User> user) {
        user.observe(this, user1 -> {
            if (user1 == null) return;
            mTextBalance.setText(String.valueOf(user1.getBalance()));
            mTextIncome.setText(String.valueOf(user1.getIncome()));
            mTextExpense.setText(String.valueOf(user1.getExpense()));
        });
    }

    @Override
    public void showFutureOrders(final MutableLiveData<List<Order>> futureOrders) {
        futureOrders.observe(this, orders -> mUpcomingAdapter.setData(orders));
    }

    @Override
    public void showPastOrders(final MutableLiveData<List<Order>> pastOrders) {
        pastOrders.observe(this, orders -> mPastAdapter.setData(orders));
    }

    @Override
    public void onItemClicked(final View view, final int position) {
        Toast.makeText(this, "Click", Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.image_logo, R.id.image_calendar, R.id.image_notifications, R.id.text_add_more})
    public void onTopClick(View view) {
        switch (view.getId()) {
            case R.id.image_logo:
                break;
            case R.id.image_calendar:
                break;
            case R.id.image_notifications:
                break;
            case R.id.text_add_more:
                if (mSheetBehavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    mSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                } else {
                    mSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                }
                break;
        }
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
        setupAdapters();
        setupNewOrder();
        getPresenter().fetchCurrentUser();
        getPresenter().fetchOrders();
    }

    private void setupNewOrder() {
        mSheetBehavior = BottomSheetBehavior.from(mBottomSheet);
        mSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        RxView.queryTextChanged(mEditPhone).observe(this, mNewOrder::setPhone);
        RxView.queryTextChanged(mEditName).observe(this, mNewOrder::setName);
        RxView.queryTextChanged(mEditDescription).observe(this, mNewOrder::setDescription);
        RxView.queryIntChanged(mEditIncome).observe(this, mNewOrder::setIncome);
        RxView.queryIntChanged(mEditExpense).observe(this, mNewOrder::setExpense);
        RxView.queryCheckedChanged(mCheckAqua).observe(this, mNewOrder::setAqua);
        RxView.queryCheckedChanged(mCheckMk).observe(this, mNewOrder::setMk);
        RxView.queryCheckedChanged(mCheckPinata).observe(this, mNewOrder::setPinata);
    }

    private void setupAdapters() {
        mUpcomingAdapter = new UpcomingPagerAdapter(getSupportFragmentManager());
//        mViewPagerUpcoming.setPageTransformer(true, new ZoomOutTransformation());
        mViewPagerUpcoming.setAdapter(mUpcomingAdapter);
        mViewPagerUpcoming.setClipToPadding(false);
        mViewPagerUpcoming.setPadding(mMargin32, 0, mMargin32, 0);
        mViewPagerUpcoming.setPageMargin(mMargin16);
        mRecyclerPastOrders.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerPastOrders.setAdapter(mPastAdapter);
    }

    @OnClick({R.id.text_date, R.id.text_time, R.id.text_hrs_minus, R.id.text_hrs_plus, R.id.image_save, R.id.image_cancel, R.id.text_equipment, R.id.children_count_minus, R.id.children_count_plus})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_date:
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(mNewOrder.getDate());
                DatePickerDialog.newInstance((datePicker, year, month, day)
                                -> {
                            mNewOrder.setDate(DateUtils.getDate(mNewOrder.getDate(), year, month, day));
                            mTextDate.setText(DateUtils.toStringDate(mNewOrder.getDate()));
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                ).show(getFragmentManager(), "date");
                break;
            case R.id.text_time:
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(mNewOrder.getDate());
                TimePickerDialog dialog1 = TimePickerDialog.newInstance((datePicker, hours, minutes, seconds)
                                -> {
                            mNewOrder.setDate(DateUtils.getDate(mNewOrder.getDate(), hours, minutes));
                            mTextTime.setText(DateUtils.toStringTime(mNewOrder.getDate()));
                        },
                        calendar1.get(Calendar.HOUR_OF_DAY),
                        calendar1.get(Calendar.MINUTE), true);
                dialog1.show(getFragmentManager(), "time");
                break;
            case R.id.text_hrs_minus:
                mNewOrder.decrementDuration();
                mTextDuration.setText(String.format(Locale.US, "%.1f h", (mNewOrder.getDuration() * 0.5f)));
                break;
            case R.id.text_hrs_plus:
                mNewOrder.incrementDuration();
                mTextDuration.setText(String.format(Locale.US, "%.1f h", (mNewOrder.getDuration() * 0.5f)));
                break;
            case R.id.image_save:
                mSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                getPresenter().saveOrder(mNewOrder);
                mNewOrder = new Order();
                break;
            case R.id.image_cancel:
                mNewOrder = new Order();
                mSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                break;
            case R.id.text_equipment:
                // todo show equipment list
                break;
            case R.id.children_count_minus:
                mNewOrder.decrementChildren();
                mTextChildrenCount.setText(String.valueOf(mNewOrder.getChildren()));
                break;
            case R.id.children_count_plus:
                mNewOrder.incrementChildren();
                mTextChildrenCount.setText(String.valueOf(mNewOrder.getChildren()));
                break;
        }
    }
}
