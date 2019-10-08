package com.shop.client;

import com.shop.api.swagger.models.ProductDto;
import com.shop.exception.ProductServiceCommunicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.shop.config.RestTemplateConfig.LOCALHOST_URL;

@Slf4j
@Component
public class ProductServiceClient {

    @Value("${client.product.port}")
    private String port;
    @Value("${client.product.prefix}")
    private String prefix;
    private final RestTemplate restTemplate;

    @Autowired
    public ProductServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ProductDto> getProducts() {
        try {
            ResponseEntity<List<ProductDto>> response = restTemplate.exchange(String.format(LOCALHOST_URL, port, prefix) + "/products",
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<ProductDto>>() {});
            return response.getBody();
        } catch (HttpClientErrorException e) {
            log.warn(e.getLocalizedMessage());
            throw new ProductServiceCommunicationException("Error during communication with product service");
        }
    }
}
