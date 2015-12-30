package com.nexusy.rabbitmq.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author lan
 * @since 2015-12-29
 */
public class Publisher {

    private static final String EXCHANGE_NAME = "news";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("10.12.13.111");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //exchange的类型:direct, topic, headers, fanout
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        String message = args[0];
        String routingKey = args[1];
        channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
        System.out.println("Sent news: " + message);

        channel.close();
        connection.close();
    }
}
