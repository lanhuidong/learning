package com.nexusy.spring.task;

import java.util.Date;

/**
 * @author lanhuidong
 * @since 2016-01-17
 */
public class QuartzDemoJob {

    public void execute() {
        System.out.println(new Date() + "quartz task." + Thread.currentThread().getName());
    }
}
