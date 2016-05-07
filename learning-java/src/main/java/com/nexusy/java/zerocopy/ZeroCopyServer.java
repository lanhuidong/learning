package com.nexusy.java.zerocopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author lanhuidong
 * @since 2016-05-07
 */
public class ZeroCopyServer {

    private static final int DEFAULT_PORT = 7000;

    private void run(int port) {
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.configureBlocking(false);
            ssc.socket().bind(new InetSocketAddress(DEFAULT_PORT));
            Selector selector = Selector.open();
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务已启动,监听端口为" + port);
            while (true) {
                System.out.println("waiting");
                int selected = selector.select();
                if (selected == 0) {
                    continue;
                }
                Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    if (key.isAcceptable()) {
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                        SocketChannel channel = serverSocketChannel.accept();
                        if (channel != null) {
                            System.out.println("建立连接");
                            channel.configureBlocking(false);
                            channel.register(selector, SelectionKey.OP_READ);
                        }
                    }
                    if (key.isReadable()) {
                        System.out.println("开始接收数据");
                        SocketChannel channel = (SocketChannel) key.channel();
                        readData(channel);
                    }
                    it.remove();
                }

            }
        } catch (IOException e) {
            System.out.println("服务启动失败: " + e.getMessage());
        }
    }

    public void readData(SocketChannel sc) {
        long totalBytes = 0;
        ByteBuffer buffer = ByteBuffer.allocate(4096);
        int nread = 0;
        while (nread != -1) {
            try {
                nread = sc.read(buffer);
                if (nread != -1) {
                    totalBytes += nread;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            buffer.rewind();
        }
        try {
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("总共收到数据: " + totalBytes + "字节");
    }

    public static void main(String[] args) {
        int port;
        if (args.length > 0) {
            port = Integer.valueOf(args[0]);
        } else {
            port = DEFAULT_PORT;
        }
        ZeroCopyServer server = new ZeroCopyServer();
        server.run(port);
    }
}
