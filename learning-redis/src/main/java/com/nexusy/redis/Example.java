package com.nexusy.redis;

import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;

/**
 * @author lanhuidong
 * @since 2015-11-28
 */
public class Example {

    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> valueOperations;

    public void addValues() {
        for (int i = 0; i < 10; i++) {
            valueOperations.set("foo" + i, "bar" + i);
        }
    }
}
