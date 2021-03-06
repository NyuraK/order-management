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

    static UserDto convertToDto(User user) {
        return new UserDto()
                .id(user.getId())
                .name(user.getName())
                .contact(user.getContact());
    }

    static User convertToEntity(UserDto userDto) {
        User user = new User();
        user.setContact(userDto.getContact());
        user.setName(userDto.getName());
        return user;
    }
}
