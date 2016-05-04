package com.nexusy.spring.ioc.beanpostprocessor;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author lan
 * @since 2016-05-03
 */
@Component
public class Foo1 {

    @PostConstruct
    public void init(){
        System.out.println("init Foo1");
    }
}
