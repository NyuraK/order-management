package com.shop.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderAlreadyPaidException extends BaseOrderManagementException {
    private final String id;

    @Override
    public String getMessage() {
        return String.format("Order with id %s was already paid", getId());
    }
}
