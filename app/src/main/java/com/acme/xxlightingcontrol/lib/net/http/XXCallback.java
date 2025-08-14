package com.acme.xxlightingcontrol.lib.net.http;

import android.util.Log;

import com.acme.xxlightingcontrol.lib.xutil.GlobalUtil;
import com.acme.xxlightingcontrol.lib.xutil.XToast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @param <T>
 * @author jx9@msn.com
 */
public abstract class XXCallback<T extends XXResponse> implements Callback<T> {

    public static final String LOG_TAG = XXCallback.class.getSimpleName();

    public static final String SUCCESS = "0:0001";

    public static final String FAILURE = "EORROR";

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            XXResponse xxResponse = response.body();
            if (!SUCCESS.equals(xxResponse.getMsg())) {
                XToast.showShortMsg(GlobalUtil.CONTEXT, xxResponse.getMsgText());
                onFailure(xxResponse.getMsg(), xxResponse.getMsgText());
            } else {
                onSuccess(xxResponse.getMsg(), xxResponse.getMsgText(), (T) xxResponse);
            }
        } else {
            try {
                Log.e(LOG_TAG, response.code() + ":" + response.raw().request().url() + " - "
                        + response.errorBody().string());
            } catch (IOException e) {
                Log.e(LOG_TAG, response.code() + ":" + response.raw().request().url());
                e.printStackTrace();
            }

            XXResponse failureResponse = new XXResponse(FAILURE, "请求失败");
            XToast.showShortMsg(GlobalUtil.CONTEXT, failureResponse.getMsgText());
            onFailure(failureResponse.getMsg(), failureResponse.getMsgText());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        XXResponse failureResponse = new XXResponse(FAILURE, "请求失败");
        Log.e(LOG_TAG, t.toString());
        onFailure(failureResponse.getMsg(), failureResponse.getMsgText());
        XToast.showShortMsg(GlobalUtil.CONTEXT, failureResponse.getMsgText());
    }

    public abstract void onSuccess(String msg, String msgText, T data);

    public abstract void onFailure(String msg, String msgText);

}
