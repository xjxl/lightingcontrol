package com.acme.xxlightingcontrol.lib.xbase;

import com.acme.xxlightingcontrol.lib.base.BaseView;

/**
 * @param <T>
 * @author jx9@msn.com
 */
public interface XBaseView<T> extends BaseView<T> {

    void success(int code, String msg, String tag, T t);

    void failure(int code, String msg, String tag);

}
