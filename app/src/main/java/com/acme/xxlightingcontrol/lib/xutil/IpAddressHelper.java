package com.acme.xxlightingcontrol.lib.xutil;

import static android.net.ConnectivityManager.NetworkCallback.FLAG_INCLUDE_LOCATION_INFO;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;

import androidx.annotation.NonNull;

import java.util.List;

/**
 * @author jx9@msn.com
 */
public class IpAddressHelper {

    public static String getWifiIpAddress(Context context) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifiManager != null) {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int ipAddress = wifiInfo.getIpAddress();
            return Formatter.formatIpAddress(ipAddress);
        }
        return null; // Or handle the case where WifiManager is null
    }

    public String getMacAddress(Context context) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifiManager != null) {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        }
        return "";
    }

    public static void ss(Context context) {
        // Obtain ConnectivityManager instance
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

// Create a NetworkCallback
        ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback(FLAG_INCLUDE_LOCATION_INFO) {
            @Override
            public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
                super.onCapabilitiesChanged(network, networkCapabilities);
                if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    WifiInfo wifiInfo = (WifiInfo) networkCapabilities.getTransportInfo();
                    if (wifiInfo != null) {
                        // Access WifiInfo properties, e.g., wifiInfo.getSSID()
                        String macAddress = wifiInfo.getMacAddress();
//                        wifiInfo.get
                    }
                }
            }

        };

        Network activityManager = connectivityManager.getActiveNetwork();
        LinkProperties linkProperties =  connectivityManager.getLinkProperties(activityManager);
        final List<LinkAddress> linkAddresses = linkProperties.getLinkAddresses();
        for (LinkAddress linkAddress: linkAddresses) {
            System.out.println(linkAddress.getAddress());
        }
// Register the NetworkCallback
        connectivityManager.registerNetworkCallback(new NetworkRequest.Builder().addTransportType(NetworkCapabilities.TRANSPORT_WIFI).build(), networkCallback);

// Unregister the callback when no longer needed
// connectivityManager.unregisterNetworkCallback(networkCallback);
    }
}