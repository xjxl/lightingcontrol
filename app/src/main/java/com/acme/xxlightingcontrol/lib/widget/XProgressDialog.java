package com.acme.xxlightingcontrol.lib.widget;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * @author jx9@msn.com
 */
public class XProgressDialog extends ProgressDialog {

    public XProgressDialog(Context context) {
        super(context);
    }

    public XProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    public static XProgressDialog newInstance(Context context, String msg) {
        XProgressDialog progressDialog = new XProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(msg);
        return progressDialog;
    }

    public static XProgressDialog newInstance(Context context, String msg, int style) {
        XProgressDialog progressDialog = new XProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setProgressStyle(style);
        progressDialog.setMessage(msg);
        return progressDialog;
    }

}
