package com.ruslanlyalko.agency.presentation.ui.dashboard.adapter.future;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * Created by Ruslan Lyalko
 * on 10.08.2018.
 */
public class ZoomOutTransformation implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.8f;
//    private static final float MIN_ALPHA = 0.7f;

    @Override
    public void transformPage(View page, float position) {
        if (position < -1) {  // [-Infinity,-1)
            // This page is way off-screen to the left.
//            page.setAlpha(0);
        } else if (position <= 1) { // [-1,1]
//            page.setScaleX(Math.max(MIN_SCALE, 1 - Math.abs(position)-0.09375f));
            page.setScaleY(Math.max(MIN_SCALE, 1 - Math.abs(position)-0.09375f));
//            page.setAlpha(Math.max(MIN_ALPHA, 1 - Math.abs(position)));
        } else {  // (1,+Infinity]
            // This page is way off-screen to the right.
//            page.setAlpha(0);
        }
    }
}
