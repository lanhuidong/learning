package com.nexusy.spring.task;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lanhuidong
 * @since 2016-01-16
 */
public class TaskDemoTest {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-task.xml");
        TaskExecutorDemo demo = ctx.getBean(TaskExecutorDemo.class);
        demo.printMessage();
    }
}
