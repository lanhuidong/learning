package com.nexusy.spring.task;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lanhuidong
 * @since 2016-01-16
 */
public class TaskAnnotationDemoTest {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(TaskAnnotationConfig.class);
    }
}
