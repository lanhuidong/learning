package com.nexusy.java.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * 重排序测试代码,由 《Java并发编程实战》程序清单16-1 改编
 * <pre>
 * 初始化:x=y=a=b=0
 * |-------------------------------|
 * |    Thread1    |    Thread2    |
 * |-------------------------------|
 * |     a=1       |     b=1       |
 * |     x=b       |     y=a       |
 * |-------------------------------|
 * 可得到4种结果:
 * 1.x=0, y=0
 * 2.x=1, y=0
 * 3.x=0, y=1
 * 4.x=1, y=1
 * </pre>
 *
 * @author lanhuidong
 * @since 2016-03-06
 */
public class ReorderDemo {

    private static int x;
    private static int y;
    private static int a;
    private static int b;

    private static boolean case1;  //x==0,y==0
    private static boolean case2;  //x==1,y==0
    private static boolean case3;  //x==0,y==1
    private static boolean case4;  //x==1,y==1


    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        while (true) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            CountDownLatch latch = new CountDownLatch(1);
            Thread t1 = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a = 1;
                x = b;
            });

            Thread t2 = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                b = 1;
                y = a;
            });
            t1.start();
            t2.start();
            latch.countDown();
            t1.join();
            t2.join();
            String result = "第" + i + "次 (" + x + "," + y + "）";
            if (x == 0 && y == 0 && !case1) {
                System.out.println(result);
                case1 = true;
            } else if (x == 1 && y == 0 && !case2) {
                System.out.println(result);
                case2 = true;
            } else if (x == 0 && y == 1 && !case3) {
                System.out.println(result);
                case3 = true;
            } else if (x == 1 && y == 1 && !case4) {
                System.out.println(result);
                case4 = true;
            }
            if (case1 && case2 && case3 && case4) {
                break;
            }

        }
    }

}
