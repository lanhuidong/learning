package com.nexusy.rabbitmq.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author lan
 * @since 2015-12-29
 */
public class Producer {

    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("10.12.13.111");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        //这是幂等操作，queue不存在时才会创建
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        channel.basicPublish("", QUEUE_NAME, null, "Hello, RabbitMQ!".getBytes("UTF-8"));
        System.out.println("Send: Hello, RabbitMQ!");
        channel.close();
        connection.close();
    }
}
