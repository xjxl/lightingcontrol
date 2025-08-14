package com.acme.xxlightingcontrol.lib.annotation;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jx9@msn.com
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, FIELD})
public @interface Progress {
    boolean isDeployAble() default true;

    boolean isDialogAble() default true;

    String beforeProgressMsg() default "即将处理";

    String progressMsg() default "处理中...";

    String afterProgressMsg() default "处理完毕";

    String errorProgressMsg() default "处理错误";

}
