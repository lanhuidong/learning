package com.nexusy.rabbitmq.spring.hello;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author lan
 * @since 2015-12-29
 */
public class Consumer {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        AmqpTemplate template = ctx.getBean(AmqpTemplate.class);
        System.out.println("Received: " + template.receiveAndConvert());
    }
}
