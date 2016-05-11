package com.nexusy.zookeeper.queue;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;

/**
 * @author lan
 * @since 2016-05-11
 */
public class ZKQueueTest {

    private CountDownLatch latch = new CountDownLatch(2);

    @Test
    public void test() {
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class Producer implements Runnable {

        @Override
        public void run() {
            ZKQueue queue = new ZKQueue("10.12.1.18:2181", "/q");
            for (int i = 0; i < 10; i++) {
                try {
                    queue.offer(String.valueOf(i).getBytes("UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Consumer implements Runnable {

        @Override
        public void run() {
            ZKQueue queue = new ZKQueue("10.12.1.18:2181", "/q");
            while (true) {
                try {
                    byte[] data = queue.take();
                    System.out.println("Consumer:" + new String(data, "UTF-8"));
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
