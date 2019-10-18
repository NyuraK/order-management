package com.shop.controller;


import com.shop.api.swagger.controllers.OrderManagementApi;
import com.shop.api.swagger.models.OrderDto;
import com.shop.service.OrderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/order-service")
@Api(tags = "Order Management")
public class OrderController implements OrderManagementApi {
    private OrderService service;

    @Autowired
    public OrderController(OrderService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<OrderDto> create(OrderDto orderDto) {
        Objects.requireNonNull(orderDto);
        return ResponseEntity.ok(service.createOrder(orderDto));
    }

    @Override
    public ResponseEntity<OrderDto> get(String id) {
        return ResponseEntity.ok(service.getOrder(id));
    }

    @Override
    public ResponseEntity<Void> delete(String id) {
        service.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return ResponseEntity.ok(service.getAllOrders());
    }

    @Override
    public ResponseEntity<OrderDto> update(String id, OrderDto orderDto) {
        Objects.requireNonNull(orderDto);
        return ResponseEntity.ok(service.updateOrder(orderDto));
    }

    @Override
    public ResponseEntity<Double> getOrderTotal(String id) {
        return ResponseEntity.ok(service.getTotal(id));
    }
}
