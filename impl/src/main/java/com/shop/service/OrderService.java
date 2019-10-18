package com.shop.service;

import com.shop.api.swagger.models.OrderDto;
import com.shop.exception.OrderNotFoundException;
import com.shop.model.Order;
import com.shop.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService {
    private OrderRepository repository;

    @Autowired
    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public OrderDto getOrder(String id) {
        log.info("Get order by id {}", id);
        Order order = repository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        return Converter.convertToDto(order);
    }

    public OrderDto createOrder(OrderDto orderDto) {
        Order order = Converter.convertToEntity(orderDto);
        return Converter.convertToDto(repository.save(order));
    }

    public void deleteOrder(String id) {
        log.info("Delete order with id {}", id);
        Order order = repository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
        repository.delete(order);
    }


    public List<OrderDto> getAllOrders() {
        log.info("Get all orders");
        return repository.findAll().stream().map(Converter::convertToDto).collect(Collectors.toList());
    }

    public OrderDto updateOrder(OrderDto orderDto) {
        log.info("Update order with id {}", orderDto.getId());
        Order order = repository.findById(orderDto.getId()).orElseThrow(() -> new OrderNotFoundException(orderDto.getId()));
        repository.save(Converter.convertToEntity(orderDto));
        return Converter.convertToDto(order);
    }

    public Double getTotal(String id) {
        Optional<Order> order = repository.findById(id);
        if (order.isPresent()) {
            return order.get().getTotal();
        }
        throw new OrderNotFoundException(id);
    }

    public void paidOrder(String orderId, Integer paymentId) {
        Order order = repository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
        order.setPaymentId(paymentId);
        repository.save(order);
    }
}
