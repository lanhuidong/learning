package com.nexusy.spring.aop.ltw;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lan
 * @since 2016-04-27
 */
public class LTWMain {

    /**
     * 运行时需要添加JVM参数：-javaagent:D:\spring-instrument-4.2.5.RELEASE.jar
     */
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-ltw.xml");
        MyService.calc1();  //非spring托管Bean也可以织入哦
        MyService myService = ctx.getBean(MyService.class);
        myService.calc2();
    }
}
