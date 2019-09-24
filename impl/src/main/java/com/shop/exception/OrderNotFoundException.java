package com.shop.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderNotFoundException extends BaseOrderManagementException {

    private String id;


    @Override
    public String getMessage() {
        return String.format("Order with id %s not found", getId());
    }
}
