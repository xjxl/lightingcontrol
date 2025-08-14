package com.acme.xxlightingcontrol.module;

import com.acme.xxlightingcontrol.lib.base.BaseView;
import com.acme.xxlightingcontrol.lib.di.AppModule;
import com.acme.xxlightingcontrol.prosenter.ModePresenter;
import com.acme.xxlightingcontrol.prosenter.ProductPresenter;
import com.acme.xxlightingcontrol.prosenter.impl.ModePresenterImpl;
import com.acme.xxlightingcontrol.prosenter.impl.ProductPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author jx9@msn.com
 */
@Module(includes = AppModule.class)
public class PresenterModule {
    private BaseView view;

    public PresenterModule(BaseView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    ProductPresenter provideProductPresenter() {
        return new ProductPresenterImpl(view);
    }

    @Provides
    @Singleton
    ModePresenter provideModePresenter() {
        return new ModePresenterImpl(view);
    }

}
