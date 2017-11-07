package com.nexusy.util;

import java.net.*;
import java.util.Enumeration;

/**
 * 获取本机的局域网IP
 *
 * @author lanhuidong
 * @since 2017-11-07
 */
public final class NetworkUtil {

    private NetworkUtil() {
    }

    public static String getLanIP() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface nInterface = interfaces.nextElement();
                Enumeration<InetAddress> addresses = nInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();
                    if (address instanceof Inet4Address && !address.isLoopbackAddress()) {
                        return address.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getLanIP2() {
        String ip = "";
        try {
            ip = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ip;
    }

    public static void main(String[] args) {
        System.out.println(getLanIP());
        System.out.println(getLanIP2());
    }

}
