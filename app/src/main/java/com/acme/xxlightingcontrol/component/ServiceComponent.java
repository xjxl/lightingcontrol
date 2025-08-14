package com.acme.xxlightingcontrol.component;

import com.acme.xxlightingcontrol.interactor.impl.ModeInteractorImpl;
import com.acme.xxlightingcontrol.interactor.impl.ProductInteractorImpl;
import com.acme.xxlightingcontrol.module.ServiceModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author jx9@msn.com
 */
@Singleton
@Component(modules = ServiceModule.class)
public interface ServiceComponent {

    void inject(ProductInteractorImpl productInteractor);

    void inject(ModeInteractorImpl modeInteractor);

}