package com.nexusy.java.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynLockTest {

    public static void main(String[] args) {
        long value = 0;
        long value1 = 0;
        long value2 = 0;
        int MAX = 10000000;
        Lock lock = new ReentrantLock();

//        long start = System.nanoTime();
//        for (int i = 0; i < MAX; i++) {
//            lock.lock();
//            try {
//                value = value + 1;
//            } finally {
//                lock.unlock();
//            }
//        }
//        long end = System.nanoTime();
//        System.out.println("lock cost: " + (end - start) / 1000000 + "ms");

        long start1 = System.nanoTime();
        for (int i = 0; i < MAX; i++) {
            synchronized (new Object()) {
                value1 = value1 + 1;
            }
        }
        long end1 = System.nanoTime();
        System.out.println("synchronized cost: " + (end1 - start1) / 1000000 + "ms");

        long start2 = System.nanoTime();
        for (int i = 0; i < MAX; i++) {
            lock.lock();
            try {
                value2 = value2 + 1;
            } finally {
                lock.unlock();
            }
        }
        long end2 = System.nanoTime();
        System.out.println("lock cost: " + (end2 - start2) / 1000000 + "ms");

    }
}

