package com.nexusy.java.zerocopy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author lanhuidong
 * @since 2016-05-07
 */
public class TraditionalClient {

    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_PORT = 7000;

    public void run(String host, int port, File file) {
        try (SocketChannel sc = SocketChannel.open();
             FileInputStream fis = new FileInputStream(file)) {
            sc.configureBlocking(true);
            sc.connect(new InetSocketAddress(host, port));
            ByteBuffer buffer = ByteBuffer.allocate(4096);
            byte[] tmp = new byte[4096];
            long t1 = System.nanoTime();
            long totalSize = 0;
            int nread;
            int nwrite;
            while ((nread = fis.read(tmp)) >= 0) {
                buffer.put(tmp, 0, nread);
                buffer.flip();
                nwrite = sc.write(buffer);
                buffer.clear();
                totalSize += nwrite;
            }
            long t2 = System.nanoTime();
            System.out.println("发送数据:" + totalSize + "字节, 耗时:" + (t2 - t1));
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
            TraditionalClient client = new TraditionalClient();
            client.run(host, port, file);
        } else {
            System.err.println(filePath + " is not found!");
        }
    }
}
