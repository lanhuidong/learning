package com.nexusy.java.design_pattern.singleton;

public class SingletonNotSafe {

    //一个静态的实例
    private static SingletonNotSafe singleton;

    //私有化构造函数
    private SingletonNotSafe() {
    }

    //给出一个公共的静态方法返回一个单一实例
    public static SingletonNotSafe getInstance() {
        if (singleton == null) {
            singleton = new SingletonNotSafe();
        }
        return singleton;
    }

}
