package com.nexusy.tomcat.connector.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lanhuidong
 * @since 2016-02-07
 */
public class HttpConnector implements Runnable {

    private boolean stoped;
    private String schema = "http";

    public String getSchema() {
        return schema;
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8080, 1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (!stoped) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
            HttpProcessor processor = new HttpProcessor();
            processor.process(socket);
        }
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }
}
