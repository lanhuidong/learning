package com.nexusy.spring.ioc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;

import java.io.IOException;

/**
 * @author lanhuidong
 * @since 2017-10-28
 */
@Configuration
@ComponentScan(basePackages = "com.nexusy.spring.ioc")
public class PrototypesTest {

    public static void main(String[] args) throws IOException {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(PrototypesTest.class);
        ctx.registerShutdownHook();
        ctx.getBean(PrototypeBean.class);
        System.in.read();
    }
}
