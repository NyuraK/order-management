package com.shop.exception;

public class ProductServiceCommunicationException extends BaseOrderManagementException {
    private final String message;

    public ProductServiceCommunicationException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
