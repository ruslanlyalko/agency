package com.ruslanlyalko.agency.presentation;

import android.app.Application;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Ruslan Lyalko
 * on 06.08.2018.
 */
public class AgencyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
    }
}
