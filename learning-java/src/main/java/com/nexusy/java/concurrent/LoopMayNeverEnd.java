package com.nexusy.java.concurrent;

/**
 * mac os x 10.11.3, jdk1.8.0_40,该程序不会停止
 *
 * @author lanhuidong
 * @since 2016-03-07
 */
public class LoopMayNeverEnd {

    boolean done = false;

    void work() throws InterruptedException {
        long i = 0L;
        while (!done) {
            i++;
        }
        System.out.println(i);
    }

    void stopWork() {
        done = true;
        System.out.println(done);
    }

    public static void main(String[] args) throws InterruptedException {
        LoopMayNeverEnd loopMayNeverEnd = new LoopMayNeverEnd();
        new Thread(() -> {
            try {
                loopMayNeverEnd.work();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(1000 * 2);
        new Thread(loopMayNeverEnd::stopWork).start();
    }
}
