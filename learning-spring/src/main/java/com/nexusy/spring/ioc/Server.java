package com.nexusy.spring.ioc;

import java.net.SocketAddress;

/**
 * @author lanhuidong
 * @since 2017-11-01
 */
public class Server {

    private SocketAddress socketAddress;

    public SocketAddress getSocketAddress() {
        return socketAddress;
    }

    public void setSocketAddress(SocketAddress socketAddress) {
        this.socketAddress = socketAddress;
    }

}
