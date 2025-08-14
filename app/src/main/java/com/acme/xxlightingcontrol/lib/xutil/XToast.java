package com.acme.xxlightingcontrol.lib.xutil;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * @author jx9@msn.com
 */
public class XToast {

    private XToast() {
        //Empty
    }

    public static void showShortMsg(Context context, String msg) {
        Toast toast = getToast(context, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static void showLongMsg(Context context, String msg) {
        Toast toast = getToast(context, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    private static Toast getToast(Context context, String msg, int length) {
        return Toast.makeText(context, msg, length);
    }

}
