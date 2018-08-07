package com.ruslanlyalko.agency.presentation.ui.profile;

import com.ruslanlyalko.agency.presentation.base.BasePresenter;

/**
 * Created by Ruslan Lyalko
 * on 06.08.2018.
 */
public class ProfilePresenter extends BasePresenter<ProfileView> {

    ProfilePresenter() {
    }

    public void fetchUser() {
        getView().showUser(getDataManager().getUser("AlCgG4ykr4UN7ZNUgRGUZ5oHaBr1"));
    }
}
