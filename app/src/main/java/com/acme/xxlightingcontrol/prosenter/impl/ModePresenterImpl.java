package com.acme.xxlightingcontrol.prosenter.impl;

import com.acme.xxlightingcontrol.component.DaggerInteractorComponent;
import com.acme.xxlightingcontrol.interactor.ModeInteractor;
import com.acme.xxlightingcontrol.lib.base.BasePresenter;
import com.acme.xxlightingcontrol.lib.base.BaseView;
import com.acme.xxlightingcontrol.lib.listener.OnResponseListener;
import com.acme.xxlightingcontrol.module.InteractorModule;
import com.acme.xxlightingcontrol.prosenter.ModePresenter;

import javax.inject.Inject;

/**
 * @author jx9@msn.com
 */
public class ModePresenterImpl extends BasePresenter implements ModePresenter,
        OnResponseListener {

    @Inject
    ModeInteractor modeInteractor;

    public ModePresenterImpl(BaseView view) {
        super(view);
    }

    @Override
    public void setupComponent() {
        DaggerInteractorComponent.builder().interactorModule(new InteractorModule()).build().inject(this);
    }

    @Override
    public void getModes() {
        modeInteractor.getModes(this);
    }

    @Override
    public void onSuccess(int code, String msg, String tag, Object o) {
        view.success(code, msg, tag, o);
    }

    @Override
    public void onFailure(int code, String msg, String tag) {
        view.failure(code, msg, tag);
    }
}