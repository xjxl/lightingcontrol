package com.acme.xxlightingcontrol.lib.base;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

/**
 * @author jx9@msn.com
 */
public class BaseApp extends Application {

    public static Application appContext;

    protected BaseApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        instance = this;
    }

    @Override
    public void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public static Application getAppContext() {
        return appContext;
    }
}
