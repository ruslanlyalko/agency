package com.ruslanlyalko.agency.presentation.ui.login;

import android.content.Intent;
import android.os.Bundle;

import com.ruslanlyalko.agency.R;
import com.ruslanlyalko.agency.presentation.base.BaseActivity;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    public static Intent getLaunchIntent(final BaseActivity activity) {
        return new Intent(activity, LoginActivity.class);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initPresenter(final Intent intent) {
        setPresenter(new LoginPresenter());
    }

    @Override
    protected void onViewReady(final Bundle savedInstanceState) {
        getPresenter().onViewReady();
    }
}
