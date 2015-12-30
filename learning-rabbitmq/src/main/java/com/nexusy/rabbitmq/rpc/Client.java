package com.nexusy.rabbitmq.rpc;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.UUID;

/**
 * @author lanhuidong
 * @since 2015-12-29
 */
public class Client {

    private Connection connection;
    private Channel channel;
    private String replyQueueName;
    private QueueingConsumer consumer;

    public Client() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("10.12.13.111");
        connection = factory.newConnection();
        channel = connection.createChannel();

        replyQueueName = channel.queueDeclare().getQueue();
        consumer = new QueueingConsumer(channel);
        channel.basicConsume(replyQueueName, true, consumer);
    }

    public String call(String message) throws Exception {
        String response;
        String corrId = UUID.randomUUID().toString();

        AMQP.BasicProperties props = new AMQP.BasicProperties
                .Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .build();

        String requestQueueName = "rpc_queue";
        channel.basicPublish("", requestQueueName, props, message.getBytes("UTF-8"));
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                response = new String(delivery.getBody(), "UTF-8");
                break;
            }
        }
        return response;
    }

    public void close() throws IOException {
        connection.close();
    }

    public static void main(String[] args) {
        Client client = null;
        try {
            client = new Client();
            System.out.println("Request fib(30)");
            String response = client.call("30");
            System.out.println("Result: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (client != null) {
                try {
                    client.close();
                } catch (IOException ignored) {
                }
            }
        }
    }
}
