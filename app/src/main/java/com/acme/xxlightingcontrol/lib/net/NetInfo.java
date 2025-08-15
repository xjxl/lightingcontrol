package com.acme.xxlightingcontrol.lib.net;

/**
 * @author jx9@msn.com
 */
public class NetInfo {
    private String macAddress;

    private String ip;

    public NetInfo() {

    }

    public NetInfo(String macAddress, String ip) {
        this.macAddress = macAddress;
        this.ip = ip;
    }

    public String getMacAddress() {
        return macAddress.toLowerCase().replace(":", "-");
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
