package com.shop.controller;


import com.shop.api.swagger.controllers.OrderManagementApi;
import com.shop.api.swagger.models.OrderDTO;
import com.shop.api.swagger.models.PaymentType;
import com.shop.service.OrderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResponseEntity<OrderDTO> create(OrderDTO orderDTO) {
        return ResponseEntity.ok(service.createOrder(orderDTO));
    }

    @Override
    public ResponseEntity<OrderDTO> get(String id) {
        return ResponseEntity.ok(service.getOrder(id));
    }

    @Override
    public ResponseEntity<Void> delete(String id) {
        service.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(service.getAllOrders());
    }

    @Override
    public ResponseEntity<OrderDTO> update(String id, OrderDTO orderDTO) {
        return ResponseEntity.ok(service.updateOrder(orderDTO));
    }

    @Override
    public ResponseEntity<OrderDTO> pay(String id, PaymentType paymentType) {
        return ResponseEntity.ok(service.pay(id, paymentType));
    }

    @Override
    public ResponseEntity<Double> getOrderTotal(String id) {
        return ResponseEntity.ok(service.getTotal(id));
    }
}
