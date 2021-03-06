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

    void showOrder(Order order);

    void showFutureOrders(MutableLiveData<List<Order>> futureOrders);

    void showPastOrders(MutableLiveData<List<Order>> pastOrders);

    void hideBottomSheet();

    void showUpcomingMenu(int position);

    void showPastMenu(int position);
}
