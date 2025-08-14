package com.acme.xxlightingcontrol.module;

import com.acme.xxlightingcontrol.lib.net.HttpClient;
import com.acme.xxlightingcontrol.service.ModeService;
import com.acme.xxlightingcontrol.service.ProductService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author jx9@msn.com
 */
@Module
public class ServiceModule {

    @Provides
    @Singleton
    ProductService provideProductService() {
        return HttpClient.getInstance().createService(ProductService.class);
    }

    @Provides
    @Singleton
    ModeService provideModeService() {
        return HttpClient.getInstance().createService(ModeService.class);
    }

}
