package com.nexusy.java.spec;

/**
 * @author lanhuidong
 * @since 2016-11-09
 */
public class SubClass extends SuperClass {

    public static int name = 0;

    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        System.out.println(SubClass.name);
        System.out.println(SuperClass.name);
    }
}
