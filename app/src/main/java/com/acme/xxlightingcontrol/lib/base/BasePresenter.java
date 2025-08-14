package com.acme.xxlightingcontrol.lib.base;

/**
 * @param <T>
 * @author jx9@msn.com
 */
public abstract class BasePresenter<T extends BaseView> {

    public T view;

    public BasePresenter() {
        setupComponent();
    }

    public BasePresenter(T view) {
        this();
        this.view = view;
    }

    public abstract void setupComponent();

    public void onDestroy() {
        if (null != view) {
            view = null;
        }
    }
}