package com.nexusy.java.spec;

/**
 * @author lanhuidong
 * @since 2016-10-04
 */
public class FloatTest {

    public static void main(String[] args) {
        System.out.println(1/0.0F);
        System.out.println(0/0.0F);
        System.out.println(Float.POSITIVE_INFINITY / Float.NEGATIVE_INFINITY);
        System.out.println(Float.POSITIVE_INFINITY / Float.POSITIVE_INFINITY);
        System.out.println(Float.MIN_EXPONENT);
        System.out.println(Float.MAX_EXPONENT);
    }
}
