package com.nexusy.java.zerocopy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @author lanhuidong
 * @since 2016-05-07
 */
public class ZeroCopyClient {

    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_PORT = 7000;

    public void run(String host, int port, File file) {
        try (SocketChannel sc = SocketChannel.open()) {
            sc.configureBlocking(true);
            sc.connect(new InetSocketAddress(host, port));
            FileChannel channel = new FileInputStream(file).getChannel();
            long fileSize = channel.size();
            long t1 = System.nanoTime();
            long sendSize = channel.transferTo(0, fileSize, sc);
            long t2 = System.nanoTime();
            System.out.println("发送数据:" + sendSize + "字节, 耗时:" + (t2 - t1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String host;
        if (args.length > 1) {
            host = args[1];
        } else {
            host = DEFAULT_HOST;
        }
        int port;
        if (args.length > 2) {
            port = Integer.valueOf(args[2]);
        } else {
            port = DEFAULT_PORT;
        }
        String filePath = args[0];
        File file = new File(filePath);
        if (file.exists()) {
            ZeroCopyClient client = new ZeroCopyClient();
            client.run(host, port, file);
        } else {
            System.err.println(filePath + " is not found!");
        }
    }
}
