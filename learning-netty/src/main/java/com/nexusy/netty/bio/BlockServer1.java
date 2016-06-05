package com.nexusy.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * @author lanhuidong
 * @since 2016-06-05
 */
public class BlockServer1 {

    public void run() throws IOException {
        ServerSocket serverSocket = new ServerSocket(7777);
        while (true) {
            Socket socket = serverSocket.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            String s;
            while ((s = br.readLine()) != null) {
                System.out.println(s);
                if("exit".equals(s)){
                    break;
                }
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
