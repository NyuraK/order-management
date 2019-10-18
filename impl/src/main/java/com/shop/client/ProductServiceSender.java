package com.shop.client;

import com.shop.api.swagger.models.PaidProductsMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class ProductServiceSender {
    @Value("${rabbitmq.products.queue}")
    private String queue;

    @Value("${rabbitmq.products.routingKey}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public ProductServiceSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Map<String, Integer> products) {
        PaidProductsMessage data = new PaidProductsMessage().products(products);
        log.info("Sending message to the queue using routingKey {}. Message= {}", rabbitTemplate.getRoutingKey(), data);
        rabbitTemplate.setRoutingKey(routingKey);
        rabbitTemplate.convertAndSend(data);
        log.info("The message has been sent to the queue.");
    }
}
