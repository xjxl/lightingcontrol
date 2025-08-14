package com.acme.xxlightingcontrol.lib.xutil;

import android.content.Context;

/**
 * @author jx9@msn.com
 */
public class GlobalUtil {

    public static Context CONTEXT;

    public static void withContext(Context context) {
        CONTEXT = context;
    }
}
