package com.shop.client;

import com.shop.api.swagger.models.PaidOrderMessage;
import com.shop.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PaymentServiceReceiver {

    private OrderService orderService;

    @Autowired
    public PaymentServiceReceiver(OrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitListener(queues = {"${rabbitmq.payment.queue}"})
    public void receiveMessage(PaidOrderMessage message) {
        log.info("Received message from queue payment queue {}.", message);
        orderService.paidOrder(message.getOrderId(), message.getPaymentId());
    }
}
