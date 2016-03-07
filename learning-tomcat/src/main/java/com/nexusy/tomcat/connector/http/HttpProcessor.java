package com.nexusy.tomcat.connector.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author lanhuidong
 * @since 2016-02-08
 */
public class HttpProcessor {

    public void process(Socket socket) {
        SocketInputStream input = null;
        OutputStream output = null;
        try {
            input = new SocketInputStream(socket.getInputStream(), 2048);
            output = socket.getOutputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void parseRequest(SocketInputStream input, OutputStream output) {

    }
}
