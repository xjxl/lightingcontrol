package com.acme.xxlightingcontrol.lib.net;

import android.util.Log;

import com.acme.xxlightingcontrol.lib.base.BaseApp;
import com.acme.xxlightingcontrol.lib.xutil.XToast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @param <T>
 * @author jx9@msn.com
 */
public abstract class XCallback<T> implements Callback<XResponse<T>> {

    private String LOG_TAG = XCallback.class.getSimpleName();

    @Override
    public void onResponse(Call<XResponse<T>> call, Response<XResponse<T>> response) {
        if (response.isSuccessful()) {
            XResponse<T> xresponse = response.body();
            assert xresponse != null;
            if (xresponse.getCode() == XResponseStatus.SUCCESS.getCode()) {
                onSuccess(xresponse.getCode(), xresponse.getMsg(), xresponse.getData());
            } else {
                onFailure(xresponse.getCode(), xresponse.getMsg());
            }
        } else {
            try {
                Log.e(LOG_TAG, response.code() + ":" + response.raw().request().url() + " - "
                        + response.errorBody().string());
            } catch (IOException e) {
                Log.e(LOG_TAG, response.code() + ":" + response.raw().request().url());
                e.printStackTrace();
            }

            XResponse<T> failureResponse = new XResponse<T>(response.code(), "请求失败");
            onFailure(failureResponse.getCode(), failureResponse.getMsg());
        }
    }

    @Override
    public void onFailure(Call<XResponse<T>> call, Throwable t) {
        XResponse<T> failureResponse = new XResponse<T>(10000, "请求失败");
        Log.e(LOG_TAG, t.toString());
        onFailure(failureResponse.getCode(), failureResponse.getMsg());
        XToast.showShortMsg(BaseApp.getAppContext(), failureResponse.getMsg());
    }

    public abstract void onSuccess(Integer code, String msg, T obj);

    public abstract void onFailure(Integer code, String msg);

}