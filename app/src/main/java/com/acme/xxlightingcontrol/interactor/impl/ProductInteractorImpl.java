package com.acme.xxlightingcontrol.interactor.impl;

import com.acme.xxlightingcontrol.common.Constants;
import com.acme.xxlightingcontrol.component.DaggerServiceComponent;
import com.acme.xxlightingcontrol.dto.ProductDto;
import com.acme.xxlightingcontrol.interactor.ProductInteractor;
import com.acme.xxlightingcontrol.lib.base.BaseInteractor;
import com.acme.xxlightingcontrol.lib.listener.OnResponseListener;
import com.acme.xxlightingcontrol.lib.net.XCallback;
import com.acme.xxlightingcontrol.lib.net.XResponse;
import com.acme.xxlightingcontrol.module.ServiceModule;
import com.acme.xxlightingcontrol.service.ProductService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * @author jx9@msn.com
 */
public class ProductInteractorImpl extends BaseInteractor implements ProductInteractor {

    @Inject
    ProductService service;

    @Override
    public void setupComponent() {
        DaggerServiceComponent.builder().serviceModule(new ServiceModule()).build().inject(this);
    }

    @Override
    public void getProducts(OnResponseListener listener) {
        Call<XResponse<List<ProductDto>>> call = service.getProducts();
        call.enqueue(new XCallback<List<ProductDto>>() {
            @Override
            public void onSuccess(Integer code, String msg, List<ProductDto> productDtos) {
                listener.onSuccess(code, msg, Constants.PRODUCTS, productDtos);
            }

            @Override
            public void onFailure(Integer code, String msg) {
                listener.onFailure(code, msg, Constants.PRODUCTS);
            }
        });
    }
}
