package com.acme.xxlightingcontrol.component;

import com.acme.xxlightingcontrol.module.PresenterModule;
import com.acme.xxlightingcontrol.view.main.home.HomeFragment;
import com.acme.xxlightingcontrol.view.main.light.LightFragment;
import com.acme.xxlightingcontrol.view.main.mode.ModeFragment;
import com.acme.xxlightingcontrol.view.main.product.ProductFragment;
import com.acme.xxlightingcontrol.view.main.product.ProductParameterFragment;
import com.acme.xxlightingcontrol.view.main.settings.SettingsFragment;
import com.acme.xxlightingcontrol.view.main.product.ProductDialogFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author jx9@msn.com
 */
@Singleton
@Component(modules = PresenterModule.class)
public interface PresenterComponent {

    void inject(HomeFragment homeFragment);

    void inject(LightFragment lightFragment);

    void inject(ProductFragment productFragment);

    void inject(ProductDialogFragment productDialogFragment);

    void inject(ProductParameterFragment productParameterFragment);

    void inject(ModeFragment modeFragment);

    void inject(SettingsFragment settingsFragment);

}