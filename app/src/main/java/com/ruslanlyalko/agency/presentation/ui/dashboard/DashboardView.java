package com.ruslanlyalko.agency.presentation.ui.dashboard;

import android.arch.lifecycle.MutableLiveData;

import com.ruslanlyalko.agency.data.models.Order;
import com.ruslanlyalko.agency.data.models.User;
import com.ruslanlyalko.agency.presentation.base.BaseView;

import java.util.List;

/**
 * Created by Ruslan Lyalko
 * on 06.08.2018.
 */
public interface DashboardView extends BaseView<DashboardPresenter> {

    void showUser(MutableLiveData<User> user);

    void setPastOrders(List<Order> list);

    void setUpcomingOrders(List<Order> list);

}
