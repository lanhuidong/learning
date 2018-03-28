package com.nexusy.java.design_pattern.singleton;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestSingleton {
 
    public static void main(String[] args) throws Exception {
        int num=100;
//        final CyclicBarrier cyclicBarrier = new CyclicBarrier(num);
        final Set<String> set= Collections.synchronizedSet(new HashSet<String>());
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i=0;i<num;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
//                        cyclicBarrier.await();//阻塞等待所有线程创建完毕，然后同时执行获取实例的操作
                        SingletonNotSafe singleton = SingletonNotSafe.getInstance();
                        set.add(singleton.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
//        Thread.sleep(2000);
        System.out.println("------并发情况下我们取到的实例------");
        for (String instance : set) {
        }
        System.out.println(set);
        executorService.shutdown();
     
    }
 
}
