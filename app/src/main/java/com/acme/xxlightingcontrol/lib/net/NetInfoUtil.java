package com.acme.xxlightingcontrol.lib.net;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * @author jx9@msn.com
 */
public class NetInfoUtil {

    public static List<NetInfo> getNetInfo() {
        List<NetInfo> netInfoList = new ArrayList<>();
        try {
            List<NetworkInterface> allNetworkInterfaces = Collections.list(NetworkInterface
                    .getNetworkInterfaces());
            for (NetworkInterface nif : allNetworkInterfaces) {
                Enumeration<InetAddress> inetAddresses = nif.getInetAddresses();
                if (!nif.isLoopback()) {
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress addr = inetAddresses.nextElement();
                        if (!addr.isLoopbackAddress()) {
                            String name = nif.getName();
                            String macAddress = getMacAddressByNetworkName(name);
                            if (addr instanceof Inet4Address) { // 只关心 IPv4 地址
                                NetInfo netInfo = new NetInfo();
                                netInfo.setMacAddress(macAddress);
                                netInfo.setIp(addr.getHostAddress());
                                netInfoList.add(netInfo);
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return netInfoList;
    }

    public static String getMacAddressByNetworkName(String networkName) {
        String macAddress = "Not able to read the MAC address";
        BufferedReader br = null;
        try {
            // Path to the file containing the MAC address for eth0
            br = new BufferedReader(new FileReader("/sys/class/net/" + networkName + "/address"));
            macAddress = br.readLine().toUpperCase(); // Read the MAC address and convert to uppercase
        } catch (IOException e) {
            e.printStackTrace(); // Handle potential IO errors
        } finally {
            if (br != null) {
                try {
                    br.close(); // Close the BufferedReader
                } catch (IOException e) {
                    e.printStackTrace(); // Handle potential errors during close
                }
            }
        }
        return macAddress;
    }

    public static String getBroadcastAddress() {
        String foundBroadcastAddress = null;
        // Prefer IPv4 stack
        System.setProperty("java.net.preferIPv4Stack", "true");
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface ni = networkInterfaces.nextElement();
                // Exclude loopback interface
                if (!ni.isLoopback()) {
                    for (InterfaceAddress interfaceAddress : ni.getInterfaceAddresses()) {
                        if (interfaceAddress.getBroadcast() != null) {
                            foundBroadcastAddress = interfaceAddress.getBroadcast().toString();
                            // Remove the leading "/" if present
                            if (foundBroadcastAddress.startsWith("/")) {
                                foundBroadcastAddress = foundBroadcastAddress.substring(1);
                            }
                            // Return the first found broadcast address
                            return foundBroadcastAddress;
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return foundBroadcastAddress;
    }
}