package com.acme.xxlightingcontrol.component;

import com.acme.xxlightingcontrol.module.InteractorModule;
import com.acme.xxlightingcontrol.prosenter.impl.ModePresenterImpl;
import com.acme.xxlightingcontrol.prosenter.impl.ProductPresenterImpl;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author jx9@msn.com
 */
@Singleton
@Component(modules = InteractorModule.class)
public interface InteractorComponent {

    void inject(ProductPresenterImpl productPresenter);

    void inject(ModePresenterImpl modePresenter);

}