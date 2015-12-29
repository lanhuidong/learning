package com.nexusy.rabbitmq.workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author lan
 * @since 2015-12-29
 */
public class Producer {
    private static final String QUEUE_NAME = "durable_task";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("10.12.13.111");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        //queue不能用不同的参数重新定义，比如已存在的queue不是durable，不能改成durable
        boolean durable = true;
        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
        String message = String.valueOf((int) (Math.random() * 9 + 1));
        //queue和message都持久化的数据才不会丢失
        channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
        System.out.println("Send: " + message);
        channel.close();
        connection.close();
    }
}
