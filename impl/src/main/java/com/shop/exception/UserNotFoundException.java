package com.shop.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserNotFoundException extends BaseOrderManagementException {
    private final String id;

    @Override
    public String getMessage() {
        return String.format("User with id %s not found", getId());
    }
}
