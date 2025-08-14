package com.acme.xxlightingcontrol.lib.net.udp;

import android.util.Log;

import java.net.InetSocketAddress;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author jx9@msn.com
 */
public class UDPClient {

    private static final String TAG = "UDPClient";

    public static UDPClient INSTANCE;

    private Channel channel;

    private String host;

    private int targetPort;

    private InetSocketAddress recipient;

    private UDPStatsManager statsManager;

    public static UDPClient getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UDPClient();
        }
        return INSTANCE;
    }

    public static UDPClient getInstance(Channel channel, String host, int targetPort, UDPStatsManager statsManager) {
        if (INSTANCE == null) {
            INSTANCE = new UDPClient(channel, host, targetPort, statsManager);
        }
        return INSTANCE;
    }

    public UDPClient() {
    }

    public UDPClient(Channel channel, String host, int targetPort, UDPStatsManager statsManager) {
        this.channel = channel;
        this.host = host;
        this.targetPort = targetPort;
        this.recipient = new InetSocketAddress(host, targetPort);
        this.statsManager = statsManager;
    }

    public void sendMessage(String message) {
        sendMessage(message, null);
    }

    public void sendMessage(String message, SendCallback callback) {
        if (channel == null || !channel.isActive()) {
            Log.e(TAG, "UDP服务未启动或通道未激活");
            if (callback != null) {
                callback.onFailure("服务未启动");
            }
            return;
        }
        ChannelFuture future = channel.writeAndFlush(new Message(message, recipient));
        future.addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) {
                if (future.isSuccess()) {
                    statsManager.sentPackets.incrementAndGet();
                    Log.d(TAG, "消息发送成功到 " + host + ":" + targetPort);
                    if (callback != null) {
                        callback.onSuccess();
                    }
                } else {
                    Log.e(TAG, "消息发送失败到 " + host + ":" + targetPort, future.cause());
                    if (callback != null) {
                        callback.onFailure(future.cause().getMessage());
                    }
                }
            }
        });
    }

    public void sendMessage(Channel channel, String message, String host, int targetPort) {
        sendMessage(channel, message, host, targetPort, null);
    }

    public void sendMessage(Channel channel, String message, String host, int targetPort, SendCallback callback) {
        if (channel == null || !channel.isActive()) {
            Log.e(TAG, "UDP服务未启动或通道未激活");
            if (callback != null) {
                callback.onFailure("服务未启动");
            }
            return;
        }
        InetSocketAddress recipient = new InetSocketAddress(host, targetPort);
        ChannelFuture future = channel.writeAndFlush(new Message(message, recipient));
        future.addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) {
                if (future.isSuccess()) {
                    statsManager.sentPackets.incrementAndGet();
                    Log.d(TAG, "消息发送成功到 " + host + ":" + targetPort);
                    if (callback != null) {
                        callback.onSuccess();
                    }
                } else {
                    Log.e(TAG, "消息发送失败到 " + host + ":" + targetPort, future.cause());
                    if (callback != null) {
                        callback.onFailure(future.cause().getMessage());
                    }
                }
            }
        });
    }
}