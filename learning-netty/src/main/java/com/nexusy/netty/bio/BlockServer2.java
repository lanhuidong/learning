package com.nexusy.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * 主线程接收到socket连接后创建新的线程处理该连接，主线程继续监听是否有新连接
 * <p/>
 * 优点：可以并发处理多个连接
 * <p/>
 * 缺点：
 * <ol>
 * <li>当有大量连接时可能导致服务器资源耗竭导致服务崩溃</li>
 * <li>线程创建销毁有一定的系统开销，没有重用线程</li>
 * <ol/>
 *
 * @author lan
 * @since 2016-06-06
 */
public class BlockServer2 {

    public void run() throws IOException {
        ServerSocket serverSocket = new ServerSocket(7777);
        while (true) {
            Socket socket = serverSocket.accept();
            Worker worker = new Worker(socket);
            new Thread(worker).start();
        }
    }

    public static void main(String[] args) throws IOException {
        BlockServer2 server = new BlockServer2();
        server.run();
    }

    public static class Worker implements Runnable {

        private Socket socket;

        public Worker(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter pw = new PrintWriter(socket.getOutputStream());
                String s;
                while ((s = br.readLine()) != null) {
                    System.out.println(s);
                    if ("exit".equals(s)) {
                        break;
                    }
                }
                Thread.sleep(10000);
                pw.write(new Date().toString());
                pw.write("\n");
                pw.flush();
                socket.close();
                System.out.println("socket closed!");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
