package com.nexusy.java.design_pattern.singleton;

public class Singleton2 {
    private static Singleton2 instance;

    private Singleton2() {
    }

    //性能较差
    public static synchronized Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
} 
