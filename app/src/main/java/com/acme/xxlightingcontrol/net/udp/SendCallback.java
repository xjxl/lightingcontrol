package com.acme.xxlightingcontrol.net.udp;

/**
 * @author jx9@msn.com
 */
public interface SendCallback {

    void onSuccess();

    void onFailure(String error);

}