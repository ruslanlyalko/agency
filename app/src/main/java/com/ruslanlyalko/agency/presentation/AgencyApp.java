package com.ruslanlyalko.agency.presentation;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.database.FirebaseDatabase;
import com.ruslanlyalko.agency.R;

import io.fabric.sdk.android.Fabric;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Ruslan Lyalko
 * on 06.08.2018.
 */
public class AgencyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(getString(R.string.renner_medium))
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
