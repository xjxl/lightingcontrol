package com.acme.xxlightingcontrol.lib.xutil;

/**
 * Some utility methods related with the String class.
 *
 * @author Pedro Vicente Gómez Sánchez
 */
public class XString {

    private static final String EMPTY_STRING = "";

    private XString() {
        //Empty
    }

    public static boolean isNullOrEmpty(final String string) {
        return string == null || EMPTY_STRING.equals(string);
    }
}
