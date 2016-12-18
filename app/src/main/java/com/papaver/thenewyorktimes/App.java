package com.papaver.thenewyorktimes;

import android.app.Application;

import com.papaver.thenewyorktimes.util.DeviceUtils;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Office on 2016-12-17.
 */

public class App extends Application {

    // ========================================================================================== //
    private static final boolean DEBUG_FLAG = true;
    private static final String DEBUG_TAG = App.class.getSimpleName();

    // ========================================================================================== //
    private static App mApp;

    public static App get() {
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        setRealmDefaultInstance();
        DeviceUtils.printDevice(mApp);
    }

    // ========================================================================================== //
    private void setRealmDefaultInstance() {
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    // ========================================================================================== //
}
