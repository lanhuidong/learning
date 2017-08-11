package com.nexusy.redis.keys;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试keys命令在大数据集下造成的阻塞
 *
 * @author lanhuidong
 * @since 2017-08-11
 */
public class RedisKeysMain {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(RedisConfig.class);
        Commands commands = ctx.getBean(Commands.class);
        commands.addKeys();
        new Thread(() -> commands.keys()).start();
        new Thread(() -> commands.getKey()).start();
    }
}
