package com.ruslanlyalko.agency.presentation.ui.dashboard;

import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

import com.ruslanlyalko.agency.R;
import com.ruslanlyalko.agency.data.models.Order;
import com.ruslanlyalko.agency.data.models.User;
import com.ruslanlyalko.agency.presentation.base.BaseActivity;
import com.ruslanlyalko.agency.presentation.ui.dashboard.adapter.past.PastOrdersAdapter;
import com.ruslanlyalko.agency.presentation.ui.dashboard.adapter.upcoming.UpcomingPagerAdapter;
import com.ruslanlyalko.agency.presentation.utils.DateUtils;
import com.ruslanlyalko.agency.presentation.utils.RxView;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;
import java.util.List;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.OnClick;

public class DashboardActivity extends BaseActivity<DashboardPresenter> implements DashboardView {

    @BindView(R.id.text_add_more) TextView mTextAddMore;
    @BindView(R.id.text_balance) TextView mTextBalance;
    @BindView(R.id.text_income) TextView mTextIncome;
    @BindView(R.id.text_expense) TextView mTextExpense;
    @BindView(R.id.recycler_past_orders) RecyclerView mRecyclerPastOrders;
    @BindView(R.id.bottom_sheet) CardView mBottomSheet;
    @BindView(R.id.view_pager_upcoming) ViewPager mViewPagerUpcoming;
    // Add order
    @BindView(R.id.image_client_avatar) ImageView mImageClientAvatar;
    @BindView(R.id.edit_name) EditText mEditName;
    @BindView(R.id.edit_phone) EditText mEditPhone;
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

    private Order mCurrentOrder = new Order();
    private BottomSheetBehavior mSheetBehavior;
    private PastOrdersAdapter mPastAdapter;
    private UpcomingPagerAdapter mUpcomingAdapter;

    public static Intent getLaunchIntent(final BaseActivity activity) {
        return new Intent(activity, DashboardActivity.class);
    }

    @Override
    public void showUser(final MutableLiveData<User> user) {
        user.observe(this, user1 -> {
            if (user1 == null) return;
            mTextBalance.setText(user1.getBalanceFormatted());
            mTextIncome.setText(user1.getIncomeFormatted());
            mTextExpense.setText(user1.getExpenseFormatted());
        });
    }

    @Override
    public void showOrder(final Order order) {
        mTextDate.requestFocus();
        mCurrentOrder = order;
        mEditPhone.setText(order.getPhone());
        mEditName.setText(order.getName());
        mTextDate.setText(DateUtils.toStringDate(order.getDate()));
        mTextTime.setText(DateUtils.toStringTime(order.getDate()));
        mEditIncome.setText(String.valueOf(order.getIncome()));
        mEditExpense.setText(String.valueOf(order.getExpense()));
        mTextDuration.setText(order.getDurationFormatted());
        mEditDescription.setText(order.getDescription());
        mCheckAqua.setChecked(order.getAqua());
        mCheckMk.setChecked(order.getMk());
        mCheckPinata.setChecked(order.getPinata());
        mTextChildrenCount.setText(order.getChildrenFormatted());
        mSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
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
    public void hideBottomSheet() {
        mSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
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
                showOrder(new Order());
                break;
        }
    }

    private void setupBottomSheet() {
        mSheetBehavior = BottomSheetBehavior.from(mBottomSheet);
        mSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        mSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull final View view, final int state) {
                mEditName.setEnabled(state == BottomSheetBehavior.STATE_EXPANDED);
                mEditPhone.setEnabled(state == BottomSheetBehavior.STATE_EXPANDED);
            }

            @Override
            public void onSlide(@NonNull final View view, final float v) {
            }
        });
    }

    @OnClick({R.id.text_date, R.id.text_time, R.id.text_hrs_minus, R.id.text_hrs_plus, R.id.image_save, R.id.image_cancel, R.id.text_equipment, R.id.children_count_minus, R.id.children_count_plus})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_date:
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(mCurrentOrder.getDate());
                DatePickerDialog.newInstance((datePicker, year, month, day)
                                -> {
                            mCurrentOrder.setDate(DateUtils.getDate(mCurrentOrder.getDate(), year, month, day));
                            mTextDate.setText(DateUtils.toStringDate(mCurrentOrder.getDate()));
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                ).show(getFragmentManager(), "date");
                break;
            case R.id.text_time:
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(mCurrentOrder.getDate());
                TimePickerDialog dialog1 = TimePickerDialog.newInstance((datePicker, hours, minutes, seconds)
                                -> {
                            mCurrentOrder.setDate(DateUtils.getDate(mCurrentOrder.getDate(), hours, minutes));
                            mTextTime.setText(DateUtils.toStringTime(mCurrentOrder.getDate()));
                        },
                        calendar1.get(Calendar.HOUR_OF_DAY),
                        calendar1.get(Calendar.MINUTE), true);
                dialog1.show(getFragmentManager(), "time");
                break;
            case R.id.text_hrs_minus:
                mCurrentOrder.decrementDuration();
                mTextDuration.setText(mCurrentOrder.getDurationFormatted());
                break;
            case R.id.text_hrs_plus:
                mCurrentOrder.incrementDuration();
                mTextDuration.setText(mCurrentOrder.getDurationFormatted());
                break;
            case R.id.image_save:
                hideKeyboard();
                mCurrentOrder.setDescription(mEditDescription.getText().toString().trim());
                mCurrentOrder.setPhone(mEditPhone.getText().toString().trim());
                mCurrentOrder.setName(mEditName.getText().toString().trim());
                mCurrentOrder.setAqua(mCheckAqua.isChecked());
                mCurrentOrder.setMk(mCheckMk.isChecked());
                mCurrentOrder.setPinata(mCheckPinata.isChecked());
                mCurrentOrder.setIncome(RxView.getInt(mEditIncome.getText().toString()));
                mCurrentOrder.setExpense(RxView.getInt(mEditExpense.getText().toString()));
                getPresenter().saveOrder(mCurrentOrder);
                break;
            case R.id.image_cancel:
                hideKeyboard();
                if (mSheetBehavior.getState() != BottomSheetBehavior.STATE_COLLAPSED)
                    mSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                else
                    mSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                break;
            case R.id.text_equipment:
                // todo show equipment list
                break;
            case R.id.children_count_minus:
                mCurrentOrder.decrementChildren();
                mTextChildrenCount.setText(mCurrentOrder.getChildrenFormatted());
                break;
            case R.id.children_count_plus:
                mCurrentOrder.incrementChildren();
                mTextChildrenCount.setText(mCurrentOrder.getChildrenFormatted());
                break;
        }
    }

    private void setupAdapters() {
        mUpcomingAdapter = new UpcomingPagerAdapter(getSupportFragmentManager(),
                (view, position) -> showOrder(mUpcomingAdapter.getData().get(position)));
//        mViewPagerUpcoming.setPageTransformer(true, new ZoomOutTransformation());
        mViewPagerUpcoming.setAdapter(mUpcomingAdapter);
        mViewPagerUpcoming.setClipToPadding(false);
        mViewPagerUpcoming.setPadding(mMargin32, 0, mMargin32, 0);
        mViewPagerUpcoming.setPageMargin(mMargin16);
        mPastAdapter = new PastOrdersAdapter(
                (view, position) -> showOrder(mPastAdapter.getData().get(position)));
        mRecyclerPastOrders.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerPastOrders.setAdapter(mPastAdapter);
    }

    @Override
    public void onBackPressed() {
        if (mSheetBehavior.getState() != BottomSheetBehavior.STATE_HIDDEN)
            mSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        else super.onBackPressed();
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
        setupBottomSheet();
        getPresenter().fetchCurrentUser();
        getPresenter().fetchOrders();
    }
}
