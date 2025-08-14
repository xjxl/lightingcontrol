package com.acme.xxlightingcontrol.lib.listener;

/**
 * @param <T>
 * @author jx9@msn.com
 */
public interface OnXXResponseListener<T> {

    void onSuccess(String msg, String msgText, String tag, T t);

    void onFailure(String msg, String msgText, String tag);

}
