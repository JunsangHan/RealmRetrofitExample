package com.papaver.thenewyorktimes.util;

import android.util.Log;

import com.papaver.thenewyorktimes.BuildConfig;


/**
 * Created by Office on 2016-12-16.
 */

public class Logger {

    public static final String TAG = "NewYork";

    public static void e(Throwable e) {
        if ( !BuildConfig.DEBUG ) {
            return;
        }
        Log.e(TAG, "", e);
    }

    public static void e(String e) {
        if ( !BuildConfig.DEBUG ) {
            return;
        }
        Log.e(TAG, e);
    }

    public static void e(boolean flag, String e) {

        if ( !BuildConfig.DEBUG || !flag) {
            return;
        }

        Log.e(TAG, e);
    }

}
