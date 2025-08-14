package com.acme.xxlightingcontrol.lib.listener;

/**
 * @param <T>
 * @author jx9@msn.com
 */
public interface OnResponseListener<T> {

    void onSuccess(int code, String msg, String tag, T t);

    void onFailure(int code, String msg, String tag);

}
