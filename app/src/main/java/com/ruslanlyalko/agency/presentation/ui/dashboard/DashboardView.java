package com.ruslanlyalko.agency.presentation.ui.dashboard;

import android.arch.lifecycle.MutableLiveData;

import com.ruslanlyalko.agency.data.models.User;
import com.ruslanlyalko.agency.presentation.base.BaseView;

/**
 * Created by Ruslan Lyalko
 * on 06.08.2018.
 */
public interface DashboardView extends BaseView<DashboardPresenter> {

    void showUser(MutableLiveData<User> user);
}
