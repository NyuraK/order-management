package com.shop.model;

import com.shop.api.swagger.models.OrderStatus;
import com.shop.api.swagger.models.ShippingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;


@Document(collection = "order-collection")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    private String id;

    private String customerId;

    private OrderStatus status;

    private ShippingType shippingType;

    private Map<String, Integer> products;

    private Integer paymentId;

    private Float total;
}
