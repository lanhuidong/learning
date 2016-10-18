package com.nexusy.java.spec;

/**
 * @author lanhuidong
 * @since 2016-10-05
 */
public class Test {
    <T extends C & I> void test(T t) {
        t.mI();
        t.mPackage();
        t.mProtected();
        t.mPublic();
    }
}
