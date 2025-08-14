package com.acme.xxlightingcontrol.lib.di;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author jx9@msn.com
 */
@Qualifier
@Retention(RUNTIME)
public @interface ForApplication {

}