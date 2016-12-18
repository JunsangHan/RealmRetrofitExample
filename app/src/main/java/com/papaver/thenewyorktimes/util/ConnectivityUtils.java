package com.papaver.thenewyorktimes.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.papaver.thenewyorktimes.App;

/**
 * Created by Office on 2016-12-17.
 */

public class ConnectivityUtils {

    public static boolean isNetworkAvailable() {
        ConnectivityManager manager =
                (ConnectivityManager) App.get().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
        if ( activeNetwork == null ) {
            return false;
        }

        if ( activeNetwork.getType() == ConnectivityManager.TYPE_WIFI ) {
            return true;
        }
        if ( activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE ) {
            return true;
        }

        return false;
    }

}
