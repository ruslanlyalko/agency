package com.ruslanlyalko.agency.presentation.ui.dashboard;

import com.ruslanlyalko.agency.presentation.base.BasePresenter;

/**
 * Created by Ruslan Lyalko
 * on 06.08.2018.
 */
public class DashboardPresenter extends BasePresenter<DashboardView> {

    DashboardPresenter() {
    }

    public void fetchUser() {
        getView().showUser(getDataManager().getUser("AlCgG4ykr4UN7ZNUgRGUZ5oHaBr1"));
    }
}
