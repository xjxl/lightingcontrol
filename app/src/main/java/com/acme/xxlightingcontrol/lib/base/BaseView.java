package com.acme.xxlightingcontrol.lib.base;

/**
 * @param <T>
 * @author jx9@msn.com
 */
public interface BaseView<T> {

    void success(int code, String msg, String tag, T t);

    void failure(int code, String msg, String tag);

}
