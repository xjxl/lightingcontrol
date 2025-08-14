package com.acme.xxlightingcontrol.module;

import com.acme.xxlightingcontrol.interactor.ModeInteractor;
import com.acme.xxlightingcontrol.interactor.ProductInteractor;
import com.acme.xxlightingcontrol.interactor.impl.ModeInteractorImpl;
import com.acme.xxlightingcontrol.interactor.impl.ProductInteractorImpl;
import com.acme.xxlightingcontrol.lib.di.AppModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author jx9@msn.com
 */
@Module(includes = AppModule.class)
public class InteractorModule {

    @Provides
    @Singleton
    ProductInteractor provideProductInteractor() {
        return new ProductInteractorImpl();
    }

    @Provides
    @Singleton
    ModeInteractor provideModeInteractor() {
        return new ModeInteractorImpl();
    }

}
