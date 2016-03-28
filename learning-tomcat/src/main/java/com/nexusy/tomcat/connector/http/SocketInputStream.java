package com.nexusy.tomcat.connector.http;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author lanhuidong
 * @since 2016-02-08
 */
public class SocketInputStream extends InputStream {

    private InputStream inputStream;
    private int bufferSize;

    public SocketInputStream(InputStream inputStream, int bufferSize) {
        this.inputStream = inputStream;
        this.bufferSize = bufferSize;
    }

    @Override
    public int read() throws IOException {
        return 0;
    }
}
