package com.nexusy.java.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author lanhuidong
 * @since 2016-03-04
 */
public class AtomicFieldUpdaterTest {

    private static final AtomicIntegerFieldUpdater UPDATER
            = AtomicIntegerFieldUpdater.newUpdater(AtomicFieldUpdaterTest.class, "i");

    private volatile int i;

    public void cas() {
        UPDATER.compareAndSet(this, 0, 1);
    }

    public static void main(String[] args) {
        AtomicInteger a = new AtomicInteger();
        a.compareAndSet(0, 1);
    }
}
