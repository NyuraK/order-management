package com.shop.exception;

public class ProductServiceUnavailableException extends BaseOrderManagementException {
    @Override
    public String getMessage() {
        return "Product service is unavailable. Cannot provide full product information";
    }
}
