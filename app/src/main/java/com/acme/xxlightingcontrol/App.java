package com.acme.xxlightingcontrol;

import android.os.SystemClock;
import android.util.Log;

import com.acme.xxlightingcontrol.common.MessageConstants;
import com.acme.xxlightingcontrol.lib.base.BaseApp;
import com.acme.xxlightingcontrol.lib.net.NetInfo;
import com.acme.xxlightingcontrol.lib.net.NetInfoUtil;
import com.acme.xxlightingcontrol.lib.net.http.HttpClient;
import com.acme.xxlightingcontrol.lib.net.udp.UDPClient;
import com.acme.xxlightingcontrol.lib.net.udp.UDPMessageListener;
import com.acme.xxlightingcontrol.lib.net.udp.UDPServer;
import com.acme.xxlightingcontrol.lib.net.udp.UDPStatsManager;

import net.time4j.android.ApplicationStarter;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author jx9@msn.com
 */
public class App extends BaseApp implements UDPMessageListener {

    public static UDPStatsManager statsManager;

    private UDPServer udpServer;

    public static UDPClient udpClient;

    public static List<NetInfo> netInfos;

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationStarter.initialize(this, true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    try {
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    netInfos = NetInfoUtil.getNetInfo();
                    if (netInfos.size() <= 0) {
                        continue;
                    }
                    HttpClient.getInstance().loggingEnabled(BuildConfig.DEBUG).timeout(BuildConfig.TIMEOUT)
                            .baseURL("http://" + netInfos.get(0).getIp() + ":" + BuildConfig.PORT);
                    initUdp();
                    break;
                }
            }
        }).start();

    }

    public void initUdp() {
        statsManager = UDPStatsManager.getInstance(this);
        statsManager.startStatsThread();
        statsManager.setMessagePrefix(netInfos.get(0).getMacAddress());
        statsManager.setMessageSplit(":");
        // 启动服务实例
        udpServer = UDPServer.getInstance(this, 10002, statsManager);
        udpServer.serverStart();
        // 初始化客户端
        new Thread(() -> {
            for (; ; ) {
                try {
                    Thread.sleep(200L);
                    if (udpServer.getChannel() == null) {
                        Log.e("APP", "等待UDP服务启动...");
                        continue;
                    }
                    udpClient = UDPClient.getInstance(udpServer.getChannel(),
                            NetInfoUtil.getBroadcastAddress(),
                            10000, statsManager);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        ping();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        udpServer.shutdown();
    }

    public void ping() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    try {
                        Thread.sleep(20000);
                        udpClient.sendMessage(MessageConstants.CHECK_CONNECT);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }

    @Override
    public void onServerStarted(int port) {

    }

    @Override
    public void onServerShutdown() {

    }

    @Override
    public void onMessageReceived(String message, String senderHost, int senderPort) {
        if (senderHost.equals(netInfos.get(0).getIp())) {
            Log.i("APP", "收到: " + senderHost + ":" + senderPort + "，消息: " + message);
        }
    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void onStats(long receivedCount, long sentCount) {

    }
}