package com.nexusy.spring.task;

import org.springframework.core.task.TaskExecutor;

/**
 * @author lanhuidong
 * @since 2016-01-16
 */
public class TaskExecutorDemo {

    private class MessagePrinterTask implements Runnable {

        private String msg;

        public MessagePrinterTask(String msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ":" + msg);
        }
    }

    private TaskExecutor taskExecutor;

    public TaskExecutorDemo(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public void printMessage() {
        for (int i = 0; i < 25; i++) {
            taskExecutor.execute(new MessagePrinterTask("Message" + i));
        }
    }
}
