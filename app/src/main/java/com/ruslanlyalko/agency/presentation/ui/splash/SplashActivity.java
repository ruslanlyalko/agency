package com.ruslanlyalko.agency.presentation.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import com.ruslanlyalko.agency.presentation.base.BaseActivity;
import com.ruslanlyalko.agency.presentation.ui.login.LoginActivity;
import com.ruslanlyalko.agency.presentation.ui.main.MainActivity;

public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashView {

    @Override
    protected void initPresenter(final Intent intent) {
        setPresenter(new SplashPresenter());
    }

    @Override
    protected void onViewReady(final Bundle savedInstanceState) {
        getPresenter().onViewReady();
    }

    @Override
    public void startMainScreen() {
        startActivity(MainActivity.getLaunchIntent(this));
        finish();
    }

    @Override
    public void startLoginScreen() {
        startActivity(LoginActivity.getLaunchIntent(this));
        finish();
    }
}
