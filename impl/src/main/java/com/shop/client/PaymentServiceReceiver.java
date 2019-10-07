package com.shop.client;

import com.shop.api.swagger.models.PaidOrderMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PaymentServiceReceiver {

    @RabbitListener(queues = {"${rabbitmq.payment.queue}"})
    public void receiveMessage(PaidOrderMessage message) {
        log.info("Received message from queue payment queue {}.", message);
    }
}
