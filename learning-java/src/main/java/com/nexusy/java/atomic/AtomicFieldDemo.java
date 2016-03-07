package com.nexusy.java.atomic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author lan
 * @since 2016-03-03
 */
public class AtomicFieldDemo {

    private volatile long a1 = 0L;
    private volatile long a2 = 0L;
    private volatile long a3 = 0L;
    private volatile long a4 = 0L;
    private volatile long a5 = 0L;
    private volatile long a6 = 0L;
    private volatile long a7 = 0L;
    private volatile long a8 = 0L;
    private volatile long a9 = 0L;
    private volatile long a10 = 0L;
    private volatile String a11 = "demo";
    private volatile String a12 = "demo";
    private volatile String a13 = "demo";
    private volatile String a14 = "demo";
    private volatile String a15 = "demo";
    private volatile String a16 = "demo";
    private volatile String a17 = "demo";
    private volatile String a18 = "demo";
    private volatile String a19 = "demo";
    private volatile String a20 = "demo";

    private static final AtomicLongFieldUpdater u1 = AtomicLongFieldUpdater.newUpdater(AtomicFieldDemo.class, "a1");
    private static final AtomicLongFieldUpdater u2 = AtomicLongFieldUpdater.newUpdater(AtomicFieldDemo.class, "a2");
    private static final AtomicLongFieldUpdater u3 = AtomicLongFieldUpdater.newUpdater(AtomicFieldDemo.class, "a3");
    private static final AtomicLongFieldUpdater u4 = AtomicLongFieldUpdater.newUpdater(AtomicFieldDemo.class, "a4");
    private static final AtomicLongFieldUpdater u5 = AtomicLongFieldUpdater.newUpdater(AtomicFieldDemo.class, "a5");
    private static final AtomicLongFieldUpdater u6 = AtomicLongFieldUpdater.newUpdater(AtomicFieldDemo.class, "a6");
    private static final AtomicLongFieldUpdater u7 = AtomicLongFieldUpdater.newUpdater(AtomicFieldDemo.class, "a7");
    private static final AtomicLongFieldUpdater u8 = AtomicLongFieldUpdater.newUpdater(AtomicFieldDemo.class, "a8");
    private static final AtomicLongFieldUpdater u9 = AtomicLongFieldUpdater.newUpdater(AtomicFieldDemo.class, "a9");
    private static final AtomicLongFieldUpdater u10 = AtomicLongFieldUpdater.newUpdater(AtomicFieldDemo.class, "a10");
    private static final AtomicReferenceFieldUpdater u11 =
            AtomicReferenceFieldUpdater.newUpdater(AtomicFieldDemo.class, String.class, "a11");
    private static final AtomicReferenceFieldUpdater u12 =
            AtomicReferenceFieldUpdater.newUpdater(AtomicFieldDemo.class, String.class, "a12");
    private static final AtomicReferenceFieldUpdater u13 =
            AtomicReferenceFieldUpdater.newUpdater(AtomicFieldDemo.class, String.class, "a13");
    private static final AtomicReferenceFieldUpdater u14 =
            AtomicReferenceFieldUpdater.newUpdater(AtomicFieldDemo.class, String.class, "a14");
    private static final AtomicReferenceFieldUpdater u15 =
            AtomicReferenceFieldUpdater.newUpdater(AtomicFieldDemo.class, String.class, "a15");
    private static final AtomicReferenceFieldUpdater u16 =
            AtomicReferenceFieldUpdater.newUpdater(AtomicFieldDemo.class, String.class, "a16");
    private static final AtomicReferenceFieldUpdater u17 =
            AtomicReferenceFieldUpdater.newUpdater(AtomicFieldDemo.class, String.class, "a17");
    private static final AtomicReferenceFieldUpdater u18 =
            AtomicReferenceFieldUpdater.newUpdater(AtomicFieldDemo.class, String.class, "a18");
    private static final AtomicReferenceFieldUpdater u19 =
            AtomicReferenceFieldUpdater.newUpdater(AtomicFieldDemo.class, String.class, "a19");
    private static final AtomicReferenceFieldUpdater u20 =
            AtomicReferenceFieldUpdater.newUpdater(AtomicFieldDemo.class, String.class, "a20");

    public static void main(String[] args) throws IOException {
        int size = 1000000;
        List<AtomicFieldDemo> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(new AtomicFieldDemo());
        }
        System.out.println(size + " AtomicFieldDemo instances has created.");
        System.in.read();
        System.out.println(list.size());
    }
}
