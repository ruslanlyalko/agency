package com.ruslanlyalko.agency.presentation.ui.main;

import android.arch.lifecycle.MutableLiveData;

import com.ruslanlyalko.agency.data.models.User;
import com.ruslanlyalko.agency.presentation.base.BaseView;

/**
 * Created by Ruslan Lyalko
 * on 06.08.2018.
 */
public interface MainView extends BaseView<MainPresenter> {

    void showUser(MutableLiveData<User> user);
}
