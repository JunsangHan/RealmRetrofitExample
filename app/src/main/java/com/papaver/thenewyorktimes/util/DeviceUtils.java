package com.papaver.thenewyorktimes.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by Office on 2016-12-17.
 */

public class DeviceUtils {

    // ========================================================================================== //
    private static final boolean DEBUG_FLAG = true;
    private static final String DEBUG_TAG = DeviceUtils.class.getSimpleName();

    // ========================================================================================== //
    public static void printDevice(Context c) {
        WindowManager manager = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(displayMetrics);
        int pxWidth = displayMetrics.widthPixels;
        int pxHeight = displayMetrics.heightPixels;

        int dpWidth = (int) (pxWidth / displayMetrics.density);
        int dpHeight = (int) (pxHeight / displayMetrics.density);

        Logger.e(DEBUG_FLAG, DEBUG_TAG + ", pxWidth = " + pxWidth + ", pxHeight = " + pxHeight);
        Logger.e(DEBUG_FLAG, DEBUG_TAG + ", dpWidth = " + dpWidth + ", dpHeight = " + dpHeight);

    }

    // ========================================================================================== //
}
