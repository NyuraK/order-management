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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class OrderService {
    private OrderRepository repository;
    private PaymentRepository paymentRepository;

    @Autowired
    public OrderService(OrderRepository repository, PaymentRepository paymentRepository) {
        this.repository = repository;
        this.paymentRepository = paymentRepository;
    }

    public OrderDTO getOrder(String id) {
        Order order = repository.findById(id).orElse(null);
        if (order != null)
            return Converter.convertToDTO(order);
        else
            throw new OrderNotFoundException(id);
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = Converter.convertToEntity(orderDTO);
        return Converter.convertToDTO(repository.save(order));
    }

    public void deleteOrder(String id) {
        Order order = repository.findById(id).orElse(null);
        if (order == null)
            throw new OrderNotFoundException(id);
        repository.delete(order);
    }

    public List<OrderDTO> getAllOrders() {
        return repository.findAll().stream()
                .map(Converter::convertToDTO).collect(Collectors.toList());
    }

    public OrderDTO updateOrder(OrderDTO orderDTO) {
        Order order = repository.findById(orderDTO.getId()).orElse(null);
        if (order == null)
            throw new OrderNotFoundException(orderDTO.getId());
        order = Converter.convertToEntity(orderDTO);
        repository.save(order);
        return Converter.convertToDTO(order);
    }

    public OrderDTO pay(String id, PaymentType paymentType) {
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
        return Converter.convertToDTO(order);
    }

    public Double getTotal(String id) {
//        Order order = repository.findById(id);
//        return .ifPresent(getTotal()).;
        return Double.valueOf(9.6);
    }
}
