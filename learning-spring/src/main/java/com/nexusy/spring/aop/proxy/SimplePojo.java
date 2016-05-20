package com.nexusy.spring.aop.proxy;

/**
 * @author lan
 * @since 2016-04-20
 */
public class SimplePojo implements Pojo {

    @Override
    public void foo() {
        System.out.println("foo in simple pojo");
        this.bar();
    }

    @Override
    public void bar() {
        System.out.println("bar in simple pojo");
    }
}
