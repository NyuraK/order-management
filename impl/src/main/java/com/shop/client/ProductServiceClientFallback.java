package com.shop.client;

import com.shop.api.swagger.models.ProductDto;
import com.shop.exception.ProductServiceUnavailableException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class ProductServiceClientFallback implements ProductServiceClient {

    @Override
    public List<ProductDto> getProducts(Set<String> productsIds) {
        throw new ProductServiceUnavailableException();
    }
}
