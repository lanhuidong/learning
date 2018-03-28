package com.nexusy.java.single_threaded_execution;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RejectedExecutionException;

public class Main7 {
    private static ExecutorService executorService = Executors.newSingleThreadExecutor();
    private static ExecutorService executorService1 = Executors.newSingleThreadExecutor();
    private static String result = "";
    private static FutureTask<String> futureTask = new FutureTask<>(() -> {
        int count = 0;
        while (count < 3) {
            System.out.println(count + "starttime");
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            } catch (RejectedExecutionException e) {
                e.printStackTrace();
            }
            System.out.println(count + "endtime");
            count++;
            System.out.println("--sotp---" + Thread.currentThread().isInterrupted());

        }
        result = count+"";
        System.out.println(result);
        System.out.println(Thread.currentThread().isAlive());
    }, result);

    public static void main(String[] args) {
        try{
            System.out.println("start");
            executorService.execute(futureTask);
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("end");
            try {
                System.out.println("count----"+futureTask.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }finally {
            executorService.shutdownNow();
            System.out.println(executorService.isShutdown());
            while (true) {
                System.out.println(executorService.isTerminated());
                if (executorService.isTerminated()) {
                    System.out.println(executorService.isTerminated());
                    return;
                }
            }
        }

    }
}
