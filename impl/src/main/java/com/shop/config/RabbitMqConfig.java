package com.shop.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfig {
    @Value("${rabbitmq.payment.topic}")
    private String topicExchange;

    @Value("${rabbitmq.payment.queue}")
    private String queue;

    @Value("${rabbitmq.payment.routingKey}")
    private String routingKey;

    @Value("${rabbitmq.products.queue}")
    private String productQueue;

    @Value("${rabbitmq.products.routingKey}")
    private String productRoutingKey;


    @Bean
    Queue queue() {
        return new Queue(queue, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchange);
    }

    @Bean
    Binding orderBinding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    @Bean
    Queue productQueue() {
        return new Queue(productQueue, false);
    }

    @Bean
    Binding productBinding(Queue productQueue, TopicExchange exchange) {
        return BindingBuilder.bind(productQueue).to(exchange).with(routingKey);
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

}
