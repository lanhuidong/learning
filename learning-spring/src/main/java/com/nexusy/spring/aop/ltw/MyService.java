package com.nexusy.spring.aop.ltw;

/**
 * @author lan
 * @since 2016-04-27
 */
public class MyService {

    public static void calc1() {
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void calc2() {
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
