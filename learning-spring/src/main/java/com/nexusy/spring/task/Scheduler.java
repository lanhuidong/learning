package com.nexusy.spring.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author lanhuidong
 * @since 2016-01-16
 */
@Component
public class Scheduler {

    private static int i = 0;

    /**
     * 固定频率的任务如果任务执行时间超过间隔时间, 则任务会累积
     * 如下面示例
     */
    @Scheduled(initialDelay = 1000, fixedRate = 4000)
    public void doTaskFixedRate() {
        if (i < 5) {
            try {

                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        i++;
        System.out.println(new Date() + " do task fixed rate " + Thread.currentThread().getName());
    }

    @Scheduled(initialDelay = 1000, fixedDelay = 60000)
    public void doTaskFixedDelay() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Date() + " do task fixed delay " + Thread.currentThread().getName());
    }

    @Scheduled(cron = "*/5 * * * * *")
    public void doTaskCron() {
        System.out.println(new Date() + " do task corn " + Thread.currentThread().getName());
    }

}
