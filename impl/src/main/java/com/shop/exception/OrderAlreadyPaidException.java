package com.shop.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderAlreadyPaidException extends BaseOrderManagementException {
    private final Integer id;

    @Override
    public String getMessage() {
        return String.format("Order with id %d was already paid", getId());
    }
}
