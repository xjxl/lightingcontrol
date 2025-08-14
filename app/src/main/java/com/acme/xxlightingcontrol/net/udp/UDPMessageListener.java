package com.acme.xxlightingcontrol.net.udp;

/**
 * @author jx9@msn.com
 */
public interface UDPMessageListener {
    void onServerStarted(int port);

    void onServerShutdown();

    void onMessageReceived(String message, String senderHost, int senderPort);

    void onError(String error);

    void onStats(long receivedCount, long sentCount);

}