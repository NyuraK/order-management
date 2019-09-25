package com.shop.service;

import com.shop.api.swagger.models.OrderDTO;
import com.shop.model.Order;

public class Converter {
    public static OrderDTO convertToDTO(Order order) {
        return new OrderDTO()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .status(order.getStatus())
                .shippingType(order.getShippingType())
                .total(order.getTotal())
                .products(order.getProducts());
    }

    public static Order convertToEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setCustomerId(orderDTO.getCustomerId());
        order.setShippingType(orderDTO.getShippingType());
        order.setStatus(orderDTO.getStatus());
        order.setProducts(orderDTO.getProducts());
        return order;
    }
}
