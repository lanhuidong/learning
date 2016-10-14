package com.nexusy.java.v8;

/**
 * {@link FunctionalInterface}注解标记的接口只能有一个抽象方法
 *
 * @author lan
 * @since 2016-10-10
 */
@FunctionalInterface
public interface Adder {

    int sum(int a, int b);

    //可以覆盖Object的方法声明
    String toString();

    //可以定义很多默认方法
    default void print() {

    }
}
