package com.nexusy.trycatch;

/**
 * @author lanhuidong
 * @since 2018-05-11
 */
public class TryCatchDemo {

    public static void main(String[] args) {
        try {
            tryFinally();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            tryResources();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void tryFinally() throws Exception {
        XxStream x = new XxStream("finally");
        try {
            x.readLine();
        } finally {
            x.close();
        }
    }

    public static void tryResources() throws Exception {
        try (XxStream x1 = new XxStream("resources")) {
            x1.readLine();
        }
    }

}
