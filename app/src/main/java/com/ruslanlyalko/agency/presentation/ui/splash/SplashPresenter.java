package com.ruslanlyalko.agency.presentation.ui.splash;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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
            getAuth().signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull final Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        getView().startDashboardScreen();
                    } else {
                        getView().showMessage("Can't login");
                    }
                }
            });
        }
    }
}
