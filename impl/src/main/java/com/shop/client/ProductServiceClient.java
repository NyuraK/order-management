package com.shop.client;

import com.shop.api.swagger.models.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;


@FeignClient(value = "product-management", fallback = ProductServiceClientFallback.class)
public interface ProductServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/products?ids={productsIds}")
    List<ProductDto> getProducts(@RequestParam("productsIds") Set<String> productsIds);

}
