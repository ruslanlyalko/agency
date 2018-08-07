package com.ruslanlyalko.agency.presentation.ui.main;

import com.ruslanlyalko.agency.presentation.base.BasePresenter;

/**
 * Created by Ruslan Lyalko
 * on 06.08.2018.
 */
public class MainPresenter extends BasePresenter<MainView> {

    MainPresenter() {
    }

    public void fetchUser() {
        getView().showUser(getDataManager().getUser("AlCgG4ykr4UN7ZNUgRGUZ5oHaBr1"));
    }
}
