package com.acme.xxlightingcontrol.lib.xbase;

/**
 * @param <T>
 * @author jx9@msn.com
 */
public abstract class XBasePresenter<T extends XBaseView> {

    public T view;

    public XBasePresenter() {
        setupComponent();
    }

    public XBasePresenter(T view) {
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