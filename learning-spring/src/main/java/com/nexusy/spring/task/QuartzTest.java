package com.nexusy.spring.task;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lanhuidong
 * @since 2016-01-17
 */
public class QuartzTest {

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("applicationContext-quartz.xml");
    }

}
