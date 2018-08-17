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

    public void fetchCurrentUser() {
        getView().showUser(getDataManager().getMyUser());
    }

    public void fetchOrders() {
        getView().showFutureOrders(getDataManager().getFutureOrders(getCurrentUser().getUid()));
        getView().showPastOrders(getDataManager().getPastOrders(getCurrentUser().getUid()));
    }
}
