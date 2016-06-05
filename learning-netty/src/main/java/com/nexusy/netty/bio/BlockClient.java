package com.nexusy.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author lanhuidong
 * @since 2016-06-05
 */
public class BlockClient {

    public void run() throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("127.0.0.1", 7777));
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        pw.write("exit\n");
        pw.flush();
        String s = br.readLine();
        System.out.println(s);
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        BlockClient client = new BlockClient();
        client.run();
    }
}
