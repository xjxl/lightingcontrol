package com.acme.xxlightingcontrol.lib.component;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.acme.xxlightingcontrol.lib.annotation.Progress;
import com.acme.xxlightingcontrol.lib.widget.XProgressDialog;
import com.acme.xxlightingcontrol.lib.xutil.XTime;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author jx9@msn.com
 */
@Aspect
public class ProgressAspect {

    public static final String TAG = "progress";

    private Object result;

    @Pointcut("execution(@me.xjliao.xjlib.annotation.Progress * *(..))")
    public void progress() {
    }

    @Before(value = "progress() && @annotation(progress)", argNames = "progress")
    public void before(JoinPoint joinPoint, Progress progress) {

    }

    @Around(value = "progress() && @annotation(progress)", argNames = "progress")
    public Object progressExecute(final ProceedingJoinPoint joinPoint, final Progress progress) {
        final Activity activity = (Activity) getContext(joinPoint.getThis());
        if (activity != null && !activity.isFinishing()) {
            final XProgressDialog progressDialog = XProgressDialog.newInstance(activity, progress.beforeProgressMsg());
            if (progress.isDialogAble() && null != progressDialog && !progressDialog.isShowing()) {
                progressDialog.show();
            }

            if (progress.isDeployAble()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(XTime.deployRequestSeconds());
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (progress.isDialogAble() && null != progressDialog && progressDialog.isShowing()) {
                                        progressDialog.setMessage(progress.progressMsg());
                                    }
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                Thread.sleep(XTime.deployRequestSeconds());
                                                result = joinPoint.proceed();
                                                if (progress.isDialogAble() && null != progressDialog && progressDialog.isShowing()) {
                                                    activity.runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            if (progress.isDialogAble() && null != progressDialog && progressDialog.isShowing()) {
                                                                progressDialog.setMessage(progress.afterProgressMsg());
                                                            }
                                                            new Thread(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    try {
                                                                        Thread.sleep(XTime.deployRequestSeconds());
                                                                        if (progress.isDialogAble() && null != progressDialog && progressDialog.isShowing()) {
                                                                            progressDialog.dismiss();
                                                                        }
                                                                    } catch (
                                                                            InterruptedException e) {
                                                                        e.printStackTrace();
                                                                    }
                                                                }
                                                            }).start();
                                                        }
                                                    });
                                                }
                                            } catch (Throwable throwable) {
                                                throwable.printStackTrace();
                                            }
                                        }
                                    }).start();
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } else {
            try {
                result = joinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }

        return result;
    }

    @After(value = "progress() && @annotation(progress)", argNames = "progress")
    public void after(JoinPoint joinPoint, Progress progress) {

    }

    @AfterThrowing(value = "progress() && @annotation(progress)", argNames = "progress", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Progress progress, RuntimeException e) {
        Log.e(TAG, e.getMessage());
    }

    public Context getContext(Object obj) {
        if (obj instanceof Activity) {
            return (Activity) obj;
        } else if (obj instanceof Fragment) {
            return ((Fragment) obj).getActivity();
        }

        return null;
    }

}
