package com.acme.xxlightingcontrol.lib.xutil;

/**
 * @author jx9@msn.com
 */
public class XTime {

    public static final double requestMinSeconds = 0.3;

    public static final double requestMaxSeconds = 0.5;

    public static long deployRequestSeconds() {
        return new Double((requestMinSeconds + (Math.random() * (requestMaxSeconds - requestMinSeconds + 0.1))) * 1000).longValue();
    }

    public static long deploySeconds(double minSeconds, double maxSeconds) {
        return new Double((minSeconds + (Math.random() * (maxSeconds - minSeconds + 0.1))) * 1000).longValue();
    }

}
