package com.ruslanlyalko.agency.presentation.ui.splash;

import com.ruslanlyalko.agency.presentation.base.BasePresenter;

/**
 * Created by Ruslan Lyalko
 * on 06.08.2018.
 */
public class SplashPresenter extends BasePresenter<SplashView> {

    SplashPresenter() {
    }

    public void onViewReady() {
        if (getCurrentUser() != null) {
            getView().startDashboardScreen();
        } else {
            getView().startLoginScreen();
        }
    }
}
