package com.nexusy.redis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author lanhuidong
 * @since 2015-11-28
 */
public class RedisMain {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-redis.xml");
        Example example = ctx.getBean(Example.class);
        System.out.println(example);
        example.addValues();
    }
}
