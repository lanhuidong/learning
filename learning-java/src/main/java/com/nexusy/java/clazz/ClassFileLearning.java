package com.nexusy.java.clazz;

import java.io.Serializable;

/**
 * 该类用于学习Class文件结构
 *
 * @author lanhuidong
 * @since 2016-05-13
 */
public class ClassFileLearning implements Serializable {

    private static final int i = 100;

    public synchronized int inc() {
        return i + 1;
    }

}
