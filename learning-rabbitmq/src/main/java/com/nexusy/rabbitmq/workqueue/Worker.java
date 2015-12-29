package com.nexusy.rabbitmq.workqueue;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author lan
 * @since 2015-12-29
 */
public class Worker {

    private static final String QUEUE_NAME = "durable_task";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("10.12.13.111");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        //这是幂等操作，queue不存在时才会创建
        boolean durable = true;
        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
        System.out.println("Waiting...");

        int prefetchCount = 1;
        channel.basicQos(prefetchCount);
        channel.basicConsume(QUEUE_NAME, false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Receive: " + message);
                try {
                    doWork(message);
                } finally {
                    channel.basicAck(envelope.getDeliveryTag(), false);
                    System.out.println("Work is finished:" + message);
                }
            }
        });
    }

    private static void doWork(String message) {
        Integer num = Integer.valueOf(message);
        try {
            Thread.sleep(num * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
