package com.acme.xxlightingcontrol.net.udp;

import android.util.Log;

import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicLong;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author jx9@msn.com
 */
public class MessageHandler extends SimpleChannelInboundHandler<ReceivedMessage> {

    private static final String TAG = "UDPMessageHandler";

    private UDPStatsManager udpStatsManager;

   public MessageHandler(UDPStatsManager udpStatsManager) {
      this.udpStatsManager = udpStatsManager;
   }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ReceivedMessage msg) {
        udpStatsManager.receivedPackets.incrementAndGet();
        String content = msg.getContent();
        InetSocketAddress sender = msg.getSender();
        Log.d(TAG, "收到UDP消息: " + content + " 来自: " + sender);
        // 通知UI线程
        this.udpStatsManager.notifyMessageReceived(content, sender);

        // 处理ping消息
//        if (content.toLowerCase().startsWith("ping")) {
//            String response = "pong: " + System.currentTimeMillis();
//            sendMessage(response, sender.getHostString(), sender.getPort());
//        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        Log.e(TAG, "UDP处理异常", cause);
        this.udpStatsManager.notifyError("处理异常: " + cause.getMessage());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Log.d(TAG, "UDP通道激活: " + ctx.channel().localAddress());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        Log.d(TAG, "UDP通道非激活: " + ctx.channel().localAddress());
    }
}