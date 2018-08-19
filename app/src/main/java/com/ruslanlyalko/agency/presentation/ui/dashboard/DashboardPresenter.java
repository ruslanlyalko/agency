package com.ruslanlyalko.agency.presentation.ui.dashboard;

import com.google.android.gms.tasks.OnSuccessListener;
import com.ruslanlyalko.agency.data.models.Order;
import com.ruslanlyalko.agency.presentation.base.BasePresenter;

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
        getView().showFutureOrders(getDataManager().getFutureOrders());
        getView().showPastOrders(getDataManager().getPastOrders());
    }

    public void saveOrder(final Order newOrder) {
        getDataManager().saveOrder(newOrder).addOnSuccessListener(aVoid -> {
            getView().hideBottomSheet();
        });
    }
}
