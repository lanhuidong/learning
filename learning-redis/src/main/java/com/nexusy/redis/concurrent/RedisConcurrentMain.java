package com.nexusy.redis.concurrent;

import com.nexusy.redis.RedisConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lanhuidong
 * @since 2015-11-28
 */
public class RedisConcurrentMain {

    static class HashIncrWorker implements Runnable {

        private HashOps ops;

        public HashIncrWorker(HashOps ops) {
            this.ops = ops;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                ops.incr("test", "f1");
                ops.incr("test", "f2");
            }
        }
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(RedisConfig.class);
        HashOps ops = ctx.getBean(HashOps.class);
        for (int i = 0; i < 10; i++) {
            new Thread(new HashIncrWorker(ops)).start();
        }
    }
}
