package com.ruslanlyalko.agency.presentation.ui.splash;

import android.arch.lifecycle.MutableLiveData;

import com.ruslanlyalko.agency.data.models.User;
import com.ruslanlyalko.agency.presentation.base.BaseView;
import com.ruslanlyalko.agency.presentation.ui.main.MainPresenter;

/**
 * Created by Ruslan Lyalko
 * on 06.08.2018.
 */
public interface SplashView extends BaseView<SplashPresenter> {

    void startMainScreen();

    void startLoginScreen();
}
