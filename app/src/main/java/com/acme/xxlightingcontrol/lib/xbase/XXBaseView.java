package com.acme.xxlightingcontrol.lib.xbase;

import com.acme.xxlightingcontrol.lib.base.BaseView;

/**
 * @param <T>
 * @author jx9@msn.com
 */
public interface XXBaseView<T> extends BaseView {

    void success(String msg, String msgText, String tag, T t);

    void failure(String msg, String msgText, String tag);

}
