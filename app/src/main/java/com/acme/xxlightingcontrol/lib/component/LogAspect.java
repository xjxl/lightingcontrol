package com.acme.xxlightingcontrol.lib.component;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author jx9@msn.com
 */
@Aspect
public class LogAspect {

    public static final String TAG = "log";

    @Pointcut("within(@com.acme.xxlightingcontrol.lib.annotation.Log *)")
    public void logClass() {
    }

    @Pointcut("execution(* *(..))")
    public void logMethod() {
    }

    @Pointcut("execution(@com.acme.xxlightingcontrol.lib.annotation.Log * *(..))")
    public void logClassMethod() {
    }

    @Pointcut("logMethod() && logClass() || logClassMethod()")
    public void log() {
    }

    @Before("log()")
    public void before(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args == null) {
            return;
        }

        Log.i(TAG, ".............................");
    }
}
