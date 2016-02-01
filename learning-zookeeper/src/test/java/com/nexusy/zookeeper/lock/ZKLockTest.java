package com.nexusy.zookeeper.lock;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author lanhuidong
 * @since 2016-01-31
 */
public class ZKLockTest {

    private static int i = 0;
    private static int j = 0;

    public static void main(String[] args) throws InterruptedException {
        incrWithoutLock();
        incrWithLock();
    }

    public static void incrWithoutLock() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch start = new CountDownLatch(1);
        for (int i = 0; i < 10000; i++) {
            executorService.execute(() -> {
                try {
                    start.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < 10; j++) {
                    ZKLockTest.i++;
                }
            });
        }
        start.countDown();
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        System.out.println("i=" + i);
    }

    public static void incrWithLock() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch start = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                try {
                    start.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < 2; j++) {
                    try {
                        ZooKeeper zk = new ZooKeeper("192.168.0.104:2181", 5000, watcher);
                        ZKLock<Integer> lock = new ZKLock<>(zk, (ZKCallback<Integer>) () -> {
                            ZKLockTest.j++;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return null;
                        });
                        lock.tryLock("/test", "count");
                    } catch (IOException | InterruptedException | KeeperException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        start.countDown();
//        executorService.shutdown();
//        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        System.out.println("j=" + j);
    }

    private static Watcher watcher = event -> {

    };

}
