package com.nexusy.mina.client;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;

/**
 * @author lanhuidong
 * @since 2016-03-27
 */
public class MinaClient {

    public static void main(String[] args) {
        IoConnector connector = new NioSocketConnector();
        ConnectFuture future = connector.connect(new InetSocketAddress(7777));
    }
}
