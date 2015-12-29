package com.nexusy.rabbitmq.helloworld;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author lan
 * @since 2015-12-29
 */
public class Consumer {

    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("10.12.13.111");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        //这是幂等操作，queue不存在时才会创建
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("Waiting...");
        channel.basicConsume(QUEUE_NAME, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Receive: " + message);
            }
        });
    }
}
