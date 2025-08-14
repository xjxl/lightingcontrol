package com.acme.xxlightingcontrol.lib.net.udp;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.channel.MultiThreadIoEventLoopGroup;
import io.netty.channel.nio.NioIoHandler;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * @author jx9@msn.com
 */
public class UDPServer {

    private static final String TAG = "UDPServer";
    private int port;
    private int workerThreads;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private EventLoopGroup group;
    private Channel channel;
    private Bootstrap bootstrap;
    private Context context;

    private WifiManager.WifiLock wifiLock;

    private static UDPServer INSTANCE;

    private UDPStatsManager statsManager;

    public static UDPServer getInstance(Context context, int port, UDPStatsManager udpStatsManager) {
        if (INSTANCE == null) {
            INSTANCE = new UDPServer(context, port, udpStatsManager);
        }
        return INSTANCE;
    }

    private UDPServer() {

    }

    public UDPServer(Context context, int port, UDPStatsManager udpStatsManager) {
        this(context, port, Math.max(2, Runtime.getRuntime().availableProcessors()), udpStatsManager);
    }

    public UDPServer(Context context, int port, int workerThreads, UDPStatsManager udpStatsManager) {
        group = new MultiThreadIoEventLoopGroup(NioIoHandler.newFactory());
        this.context = context.getApplicationContext();
        this.port = port;
        this.workerThreads = workerThreads;
        this.statsManager = udpStatsManager;
    }

    public void serverStart() {
        if (!running.compareAndSet(false, true)) {
            Log.w(TAG, "UDP服务器已经在运行中");
            return;
        }

        Log.i(TAG, "启动UDP服务器，端口: " + port + ", 工作线程数: " + workerThreads);
        acquireWifiLock();
        // 在后台线程中启动
        new Thread(() -> {
            try {
                startInternal();
            } catch (Exception e) {
                Log.e(TAG, "UDP服务器启动失败", e);
                statsManager.notifyError("启动失败：" + e.getMessage());
                shutdown();
            }
        }, "UdpServerInit").start();
    }

    private void startInternal() throws Exception {
        bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_BROADCAST, true)
                .option(ChannelOption.SO_REUSEADDR, true)
                .option(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(8192))
                .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                // Android优化：减少缓冲区大小节省内存
                .option(ChannelOption.SO_RCVBUF, 64 * 1024)
                .option(ChannelOption.SO_SNDBUF, 64 * 1024)
                .handler(new ChannelInitializer<NioDatagramChannel>() {
                    @Override
                    protected void initChannel(NioDatagramChannel ch) {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast("decoder", new DatagramStringDecoder());
                        pipeline.addLast("encoder", new DatagramStringEncoder());
                        pipeline.addLast("handler", new MessageHandler(statsManager));
                    }
                });

        // 绑定端口并启动
        ChannelFuture future = bootstrap.bind(port).sync();
        channel = future.channel();
        Log.i(TAG, "UDP服务器启动成功，监听端口: " + port);
        statsManager.notifyStarted(port);
    }

    /**
     * 优雅关闭服务器
     */
    public void shutdown() {
        if (!running.compareAndSet(true, false)) {
            Log.w(TAG, "UDP服务器未在运行");
            return;
        }

        Log.i(TAG, "开始关闭UDP服务器...");
        // 在后台线程中关闭
        new Thread(() -> {
            try {
                if (channel != null) {
                    channel.close().sync();
                }
            } catch (Exception e) {
                Log.e(TAG, "关闭通道时出错", e);
            } finally {
                if (group != null) {
                    group.shutdownGracefully(1, 5, TimeUnit.SECONDS);
                }

                Log.i(TAG, "UDP服务器关闭完成");
                releaseWifiLock();
                statsManager.notifyShutdown();
            }
        }, "UdpServerShutdown").start();
    }



    private void acquireWifiLock() {
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            if (wifiManager != null) {
                wifiLock = wifiManager.createWifiLock(WifiManager.WIFI_MODE_FULL_HIGH_PERF, TAG);
                wifiLock.acquire();
                Log.d(TAG, "WiFi锁已获取");
            }
        } catch (Exception e) {
            Log.w(TAG, "获取WiFi锁失败", e);
        }
    }

    private void releaseWifiLock() {
        try {
            if (wifiLock != null && wifiLock.isHeld()) {
                wifiLock.release();
                Log.d(TAG, "WiFi锁已释放");
            }
        } catch (Exception e) {
            Log.w(TAG, "释放WiFi锁失败", e);
        }
    }

    public boolean isRunning() {
        return running.get();
    }

    public int getPort() {
        return port;
    }

    public EventLoopGroup getGroup() {
        return group;
    }

    public void setGroup(EventLoopGroup group) {
        this.group = group;
    }

    public UDPStatsManager getStatsManager() {
        return statsManager;
    }

    public void setStatsManager(UDPStatsManager statsManager) {
        this.statsManager = statsManager;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}