package com.acme.xxlightingcontrol.lib.net.udp;

import android.util.Log;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author jx9@msn.com
 */
public class UDPStatsManager {

    private static UDPStatsManager INSTANCE;

    private UDPMessageListener udpMessageListener;

    private ScheduledExecutorService scheduler;

    private static final String TAG = "UDPStatsManger";

    public final AtomicLong sentPackets = new AtomicLong(0);

    public final AtomicLong receivedPackets = new AtomicLong(0);

    public static UDPStatsManager getInstance(UDPMessageListener udpMessageListener) {
        if (INSTANCE == null) {
            INSTANCE = new UDPStatsManager(udpMessageListener);
        }
        return INSTANCE;
    }

    public UDPStatsManager(UDPMessageListener udpMessageListener) {
        this.udpMessageListener = udpMessageListener;
        scheduler = Executors.newScheduledThreadPool(1);
    }

    public void startStatsThread() {
        scheduler.scheduleWithFixedDelay(() -> {
            long received = receivedPackets.get();
            long sent = sentPackets.get();
            Log.i(TAG, "UDP统计 - 接收: " + received + ", 发送: " + sent);

            // 通知UI线程
            if (udpMessageListener != null) {
                udpMessageListener.onStats(received, sent);
            }
        }, 30, 30, TimeUnit.SECONDS);
    }

    // 通知方法
    public void notifyStarted(int port) {
        if (udpMessageListener != null) {
            udpMessageListener.onServerStarted(port);
        }
    }

    public void notifyShutdown() {
        if (udpMessageListener != null) {
            udpMessageListener.onServerShutdown();
        }
    }

    public void notifyError(String error) {
        if (udpMessageListener != null) {
            udpMessageListener.onError(error);
        }
    }

    public void notifyMessageReceived(String message, InetSocketAddress sender) {
        if (udpMessageListener != null) {
            udpMessageListener.onMessageReceived(message,
                    sender.getHostString(), sender.getPort());
        }
    }

}
