package com.acme.xxlightingcontrol;

import android.os.SystemClock;
import android.util.Log;

import com.acme.xxlightingcontrol.lib.base.BaseApp;
import com.acme.xxlightingcontrol.lib.net.HttpClient;
import com.acme.xxlightingcontrol.lib.xutil.IpAddressHelper;
import com.acme.xxlightingcontrol.net.udp.UDPClient;
import com.acme.xxlightingcontrol.net.udp.UDPMessageListener;
import com.acme.xxlightingcontrol.net.udp.UDPServer;
import com.acme.xxlightingcontrol.net.udp.UDPStatsManager;

import net.time4j.android.ApplicationStarter;

import java.util.concurrent.TimeUnit;

/**
 * @author jx9@msn.com
 */
public class App extends BaseApp implements UDPMessageListener {

    public static UDPStatsManager statsManager;

    private UDPServer udpServer;

    public static UDPClient udpClient;

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationStarter.initialize(this, true);
        SystemClock.sleep(TimeUnit.SECONDS.toMillis(3));
        String ip = IpAddressHelper.getWifiIpAddress(this);
        HttpClient.getInstance().loggingEnabled(BuildConfig.DEBUG).timeout(BuildConfig.TIMEOUT)
                .baseURL("http://" + ip + ":" + BuildConfig.PORT);
        initUdp();
    }

    public void initUdp() {

        // 启动状态管理
        statsManager = UDPStatsManager.getInstance(this);
        statsManager.startStatsThread();
        // 启动服务实例
        udpServer = UDPServer.getInstance(this, 10002, statsManager);
        udpServer.serverStart();
        // 初始化客户端
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    try {
                        Thread.sleep(1000L);
                        if (udpServer.getChannel() == null) {
                            Log.e("APP", "等待UDP服务启动...");
                            continue;
                        }
                        udpClient = UDPClient.getInstance(udpServer.getChannel(), "2.255.255.255", 10000, statsManager);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        udpServer.shutdown();
    }

    @Override
    public void onServerStarted(int port) {

    }

    @Override
    public void onServerShutdown() {

    }

    @Override
    public void onMessageReceived(String message, String senderHost, int senderPort) {

    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void onStats(long receivedCount, long sentCount) {

    }
}