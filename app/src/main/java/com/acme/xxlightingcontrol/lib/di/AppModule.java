package com.acme.xxlightingcontrol.lib.di;

import android.content.Context;

import com.acme.xxlightingcontrol.lib.base.BaseApp;
import com.acme.xxlightingcontrol.lib.xutil.XSharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author jx9@msn.com
 */
@Module
public class AppModule {

    private Context mContext;

    public AppModule() {

    }

    public AppModule(Context context) {
        this.mContext = context;
    }

    @Provides
    @Singleton
    public Context providerContext() {
        if (this.mContext == null) {
            return BaseApp.getAppContext();
        }

        return this.mContext;
    }

    @Provides
    @Singleton
    public XSharedPreferences provideSharedPreferences() {
        return new XSharedPreferences(this.mContext == null ? BaseApp.getAppContext() : this.mContext);
    }

}
