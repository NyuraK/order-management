package com.shop.client;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class ProductServiceClient {

    private RestTemplate restTemplate;

    @Autowired
    public ProductServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

//    public List<Process> getProductsById(List<String> ids) {
//        ResponseEntity<Process> resp = restTemplate.getForEntity("http://localhost:8082/product-management/products/?list={ids}", Process.class, ids);
//    }
}
