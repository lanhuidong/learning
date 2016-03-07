package com.nexusy.java.atomic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author lan
 * @since 2016-03-03
 */
public class AtomicDemo {

    private AtomicLong a1 = new AtomicLong();
    private AtomicLong a2 = new AtomicLong();
    private AtomicLong a3 = new AtomicLong();
    private AtomicLong a4 = new AtomicLong();
    private AtomicLong a5 = new AtomicLong();
    private AtomicLong a6 = new AtomicLong();
    private AtomicLong a7 = new AtomicLong();
    private AtomicLong a8 = new AtomicLong();
    private AtomicLong a9 = new AtomicLong();
    private AtomicLong a10 = new AtomicLong();
    private AtomicReference<String> a11 = new AtomicReference<>("demo");
    private AtomicReference<String> a12 = new AtomicReference<>("demo");
    private AtomicReference<String> a13 = new AtomicReference<>("demo");
    private AtomicReference<String> a14 = new AtomicReference<>("demo");
    private AtomicReference<String> a15 = new AtomicReference<>("demo");
    private AtomicReference<String> a16 = new AtomicReference<>("demo");
    private AtomicReference<String> a17 = new AtomicReference<>("demo");
    private AtomicReference<String> a18 = new AtomicReference<>("demo");
    private AtomicReference<String> a19 = new AtomicReference<>("demo");
    private AtomicReference<String> a20 = new AtomicReference<>("demo");

    public static void main(String[] args) throws IOException {
        int size = 1000000;
        List<AtomicDemo> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(new AtomicDemo());
        }
        System.out.println(size + " AtomicDemo instances has created.");
        System.in.read();
        System.out.println(list.size());
    }
}
