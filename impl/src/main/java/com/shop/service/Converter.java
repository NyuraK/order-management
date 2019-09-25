package com.shop.service;

import com.shop.api.swagger.models.OrderDto;
import com.shop.api.swagger.models.UserDto;
import com.shop.model.Order;
import com.shop.model.User;

class Converter {
    static OrderDto convertToDto(Order order) {
        return new OrderDto()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .status(order.getStatus())
                .shippingType(order.getShippingType())
                .total(order.getTotal())
                .products(order.getProducts());
    }

    static Order convertToEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setCustomerId(orderDto.getCustomerId());
        order.setShippingType(orderDto.getShippingType());
        order.setStatus(orderDto.getStatus());
        order.setProducts(orderDto.getProducts());
        return order;
    }

    static UserDto convertToDto(User entity) {
        return new UserDto();
    }

    static User convertToEntity(UserDto dto) {
        return new User();
    }
}
