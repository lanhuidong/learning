package com.nexusy.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * 单线程处理请求，每次只能处理一个请求，处理完之后才能接受下一个请求
 *
 * @author lanhuidong
 * @since 2016-06-05
 */
public class BlockServer1 {

    public void run() throws IOException {
        ServerSocket serverSocket = new ServerSocket(7777);
        while (true) {
            //接受客户端连接，当没有连接到来时，这步会阻塞
            Socket socket = serverSocket.accept();
            //获取一个输入流，接收客户端发送的数据
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //获取一个输出流，向客户端发送数据
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            String s;
            while ((s = br.readLine()) != null) {
                System.out.println(s);
                if ("exit".equals(s)) {
                    break;
                }
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pw.write(new Date().toString());
            pw.write("\n");
            pw.flush();
            socket.close();
            System.out.println("socket closed!");
        }
    }

    public static void main(String[] args) throws IOException {
        BlockServer1 server = new BlockServer1();
        server.run();
    }
}
