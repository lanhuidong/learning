package com.nexusy.redis.pipelining;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * @author lanhuidong
 * @since 2015-04-23
 */
public class PipeliningTest {

    public static final String HOST = "www.nexusy.com";

    public static void testUsePipelining() {
        long startTime = System.nanoTime();
        Jedis jedis = new Jedis(HOST);
        Pipeline pipeline = jedis.pipelined();
        for (int i = 0; i < 1000; i++) {
            pipeline.set("foo" + i, "bar" + i);
        }
        pipeline.syncAndReturnAll();
        long endTime = System.nanoTime();
        jedis.close();
        System.out.println(endTime - startTime);
    }

    public static void testNotUsePipelining() {
        long startTime = System.nanoTime();
        Jedis jedis = new Jedis(HOST);
        for (int i = 0; i < 1000; i++) {
            jedis.set("bar" + i, "foo" + i);
        }
        long endTime = System.nanoTime();
        jedis.close();
        System.out.println(endTime - startTime);
    }

    public static void main(String[] args) {
        testNotUsePipelining(); //4241824481
        testUsePipelining();  //56828445
    }
}
