package com.acme.xxlightingcontrol.interactor.impl;

import com.acme.xxlightingcontrol.common.Constants;
import com.acme.xxlightingcontrol.component.DaggerServiceComponent;
import com.acme.xxlightingcontrol.dto.ModeDto;
import com.acme.xxlightingcontrol.interactor.ModeInteractor;
import com.acme.xxlightingcontrol.lib.base.BaseInteractor;
import com.acme.xxlightingcontrol.lib.listener.OnResponseListener;
import com.acme.xxlightingcontrol.lib.net.XCallback;
import com.acme.xxlightingcontrol.lib.net.XResponse;
import com.acme.xxlightingcontrol.module.ServiceModule;
import com.acme.xxlightingcontrol.service.ModeService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * @author jx9@msn.com
 */
public class ModeInteractorImpl extends BaseInteractor implements ModeInteractor {

    @Inject
    ModeService service;

    @Override
    public void setupComponent() {
        DaggerServiceComponent.builder().serviceModule(new ServiceModule()).build().inject(this);
    }

    @Override
    public void getModes(OnResponseListener listener) {
        Call<XResponse<List<ModeDto>>> call = service.getModes();
        call.enqueue(new XCallback<List<ModeDto>>() {
            @Override
            public void onSuccess(Integer code, String msg, List<ModeDto> modeDtos) {
                listener.onSuccess(code, msg, Constants.MODES, modeDtos);
            }

            @Override
            public void onFailure(Integer code, String msg) {
                listener.onFailure(code, msg, Constants.MODES);
            }
        });
    }
}
