package com.nexusy.redis.concurrent;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author lan
 * @since 2015-12-07
 */
@Component
public class HashOps {

    @Resource(name = "redisTemplate")
    private HashOperations<String, String, Long> hashOp;

    Long incr(String key, String hashKey) {
        return hashOp.increment(key, hashKey, 1);
    }
}
