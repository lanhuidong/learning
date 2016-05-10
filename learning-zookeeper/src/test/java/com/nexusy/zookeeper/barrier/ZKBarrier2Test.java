package com.nexusy.zookeeper.barrier;

import org.apache.zookeeper.KeeperException;
import org.junit.Test;

import java.util.Date;
import java.util.Random;

/**
 * @author lan
 * @since 2016-05-10
 */
public class ZKBarrier2Test {

    @Test
    public void test() {
        System.out.println("before enter barrier");
        ZKBarrier2 zkBarrier = new ZKBarrier2("10.12.1.18:2181", "/b", 3);
        try {
            zkBarrier.enter();
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Date() + "start do work");
        Random rand = new Random();
        int r = rand.nextInt(100);
        for (int i = 0; i < r; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(new Date() + "finish work");
        try {
            zkBarrier.leave();
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Date() + "after leave barrier");
    }
}
