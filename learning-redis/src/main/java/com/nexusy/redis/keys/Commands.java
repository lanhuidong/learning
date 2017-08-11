package com.nexusy.redis.keys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author lanhuidong
 * @since 2017-08-11
 */
@Component
public class Commands {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void addKeys() {
        for (int i = 0; i < 10000000; i++) {
            redisTemplate.opsForValue().set("k_" + i, "v_" + i);
        }
    }

    public void keys() {
        long t1 = System.nanoTime();
        redisTemplate.keys("k_1000000");
        long t2 = System.nanoTime();
        System.out.println("keys: " + (t2 - t1));
    }

    public void getKey() {
        long t1 = System.nanoTime();
        redisTemplate.opsForValue().get("k_9999999");
        long t2 = System.nanoTime();
        System.out.println("get: " + (t1 - t2));
    }

}
