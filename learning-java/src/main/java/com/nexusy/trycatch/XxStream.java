package com.nexusy.trycatch;

/**
 * @author lanhuidong
 * @since 2018-05-11
 */
public class XxStream implements AutoCloseable {

    private String id;

    public XxStream(String id) {
        this.id = id;
    }

    public String readLine() throws Exception {
        System.out.println(id + "read line");
        throw new RuntimeException(id + "readLine");
    }

    @Override
    public void close() throws Exception {
        System.out.println(id + "close");
        throw new RuntimeException(id + "close");
    }

}
