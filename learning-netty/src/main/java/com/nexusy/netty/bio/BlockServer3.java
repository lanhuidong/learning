package com.nexusy.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 主线程接收到socket连接后由线程池中的线程处理该连接，主线程继续监听是否有新连接
 * <p/>
 * 优点：
 * <ol>
 * <li>可以并发处理多个连接</li>
 * <li>不会因为过度消耗系统资源而崩溃</li>
 * <li>重用线程，减少线程创建的系统开销</li>
 * </ol>
 * <p/>
 * 缺点：
 * <ol>
 * <li>线程的利用率不高，比如线程由于读写磁盘而挂起时，其他连接不能使用该线程</li>
 * <ol/>
 *
 * @author lan
 * @since 2016-06-06
 */
public class BlockServer3 {

    private ExecutorService executors = Executors.newFixedThreadPool(10);

    public void run() throws IOException {
        ServerSocket serverSocket = new ServerSocket(7777);
        while (true) {
            Socket socket = serverSocket.accept();
            Worker worker = new Worker(socket);
            executors.execute(worker);
        }
    }

    public static void main(String[] args) throws IOException {
        BlockServer3 server = new BlockServer3();
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
