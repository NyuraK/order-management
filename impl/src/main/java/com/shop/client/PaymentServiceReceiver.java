package com.shop.client;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @RabbitListener(queues = {"${rabbitmq.payment.queue}"})
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
    }
}
