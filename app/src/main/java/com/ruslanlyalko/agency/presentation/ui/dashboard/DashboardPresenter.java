package com.ruslanlyalko.agency.presentation.ui.dashboard;

import com.ruslanlyalko.agency.data.models.Order;
import com.ruslanlyalko.agency.presentation.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ruslan Lyalko
 * on 06.08.2018.
 */
public class DashboardPresenter extends BasePresenter<DashboardView> {

    DashboardPresenter() {
    }

    public void fetchOrders() {
        List<Order> list = new ArrayList<>();
        list.add(new Order());
        list.add(new Order());
        list.add(new Order());
        getView().setPastOrders(list);
        getView().setUpcomingOrders(list);
    }
}
