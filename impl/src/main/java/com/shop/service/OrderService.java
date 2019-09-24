package com.shop.service;

import com.shop.api.swagger.models.OrderDTO;
import com.shop.api.swagger.models.OrderStatus;
import com.shop.api.swagger.models.PaymentType;
import com.shop.exception.OrderAlreadyPaidException;
import com.shop.exception.OrderNotFoundException;
import com.shop.model.Order;
import com.shop.model.Payment;
import com.shop.repository.OrderRepository;
import com.shop.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService {
    private OrderRepository repository;
    private PaymentRepository paymentRepository;

    @Autowired
    public OrderService(OrderRepository repository, PaymentRepository paymentRepository) {
        this.repository = repository;
        this.paymentRepository = paymentRepository;
    }

    public OrderDTO getOrder(String id) {
        log.info("Get order by id {}", id);
        Order order = repository.findById(id).orElse(null);
        if (order != null)
            return convertToDTO(order);
        else
            throw new OrderNotFoundException(id);
    }
    private OrderDTO convertToDTO(Order order) {
        return new OrderDTO()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .status(order.getStatus())
                .shippingType(order.getShippingType())
                .total(order.getTotal());
    }

    private Order convertToEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setCustomerId(orderDTO.getCustomerId());
        order.setShippingType(orderDTO.getShippingType());
        order.setStatus(orderDTO.getStatus());
        order.setPromocode(orderDTO.getPromocode());
        return order;
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        log.info("Create new order");
        Order order = convertToEntity(orderDTO);
        return convertToDTO(repository.save(order));
    }

    public void deleteOrder(String id) {
        log.info("Delete order with id {}", id);
        Order order = repository.findById(id).orElse(null);
        if (order == null) {
            throw new OrderNotFoundException(id);
        }

        repository.delete(order);
    }

    public List<OrderDTO> getAllOrders() {
        log.info("Get all orders");
        return repository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public OrderDTO updateOrder(OrderDTO orderDTO) {
        log.info("Update order with id {}", orderDTO.getId());
        Order order = repository.findById(orderDTO.getId()).orElse(null);
        if (order == null)
            throw new OrderNotFoundException(orderDTO.getId());
        order = convertToEntity(orderDTO);
        repository.save(order);
        return convertToDTO(order);
    }

    public OrderDTO pay(String id, PaymentType paymentType) {
        log.info("Perform payment for order with id {}", id);
        Order order = repository.findById(id).orElse(null);
        if (order == null)
            throw new OrderNotFoundException(id);
        if (order.getStatus() == OrderStatus.PAID)
            throw new OrderAlreadyPaidException(id);
        Payment payment = new Payment();
        payment.setCustomerId(order.getCustomerId());
        payment.setOrder(order);
        payment.setPaymentType(paymentType);
        paymentRepository.save(payment);
        order.setStatus(OrderStatus.PAID);
        return convertToDTO(order);
    }
}
