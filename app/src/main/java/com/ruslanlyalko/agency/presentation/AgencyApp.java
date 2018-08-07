package com.ruslanlyalko.agency.presentation;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.database.FirebaseDatabase;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Ruslan Lyalko
 * on 06.08.2018.
 */
public class AgencyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        Fabric.with(this, new Crashlytics());
    }
}
