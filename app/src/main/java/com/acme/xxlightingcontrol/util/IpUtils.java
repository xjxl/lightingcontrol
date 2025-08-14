package com.acme.xxlightingcontrol.util;

public class IpUtils {
    public static String generateBroadcastIP(String ip) {
        int suffixIndex = ip.indexOf(".");
        String suffix = ip.substring(0, suffixIndex);
        return suffix + ".255.255.255";
    }
}
