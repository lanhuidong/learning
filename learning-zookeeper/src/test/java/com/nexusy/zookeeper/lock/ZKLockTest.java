package com.nexusy.zookeeper.lock;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lanhuidong
 * @since 2016-01-31
 */
public class ZKLockTest {

    private static int i = 0;
    private static int j = 0;
    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        ZKLockTest test = new ZKLockTest();
        test.incrWithoutLock();
        test.incrWithLock();
    }

    public void incrWithoutLock() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch start = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                try {
                    start.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < 1000; j++) {
                    ZKLockTest.i++;
                }
            });
        }
        start.countDown();
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        System.out.println("i=" + i);
    }

    public void incrWithLock() throws InterruptedException {
        int taskNum = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(taskNum);
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch ready = new CountDownLatch(taskNum * 100);
        ZooKeeper zk = null;
        try {
            zk = new ZooKeeper("10.12.1.18:2181", 5000, watcher);
            for (int i = 0; i < taskNum; i++) {
                executorService.execute(new Task(zk, start, ready));
            }
            start.countDown();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ready.await();
            if (zk != null) {
                zk.close();
            }
        }
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);

        System.out.println("j=" + j);
    }

    private class Task implements Runnable {

        private ZooKeeper zk;
        private CountDownLatch start;
        private CountDownLatch ready;

        public Task(ZooKeeper zk, CountDownLatch start, CountDownLatch ready) {
            this.zk = zk;
            this.start = start;
            this.ready = ready;
        }

        @Override
        public void run() {
            try {
                start.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count.getAndIncrement();
            for (int k = 0; k < 100; k++) {
                try {
                    ZKLock<Integer> lock = new ZKLock<>(zk, () -> {
                        ZKLockTest.j++;
                        return ZKLockTest.j;
                    });
                    lock.tryLock("/test", "count");
                    lock.get();
                    ready.countDown();
                } catch (InterruptedException | KeeperException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static Watcher watcher = event -> {

    };

}
