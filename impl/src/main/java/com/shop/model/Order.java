package com.shop.model;

import com.shop.api.swagger.models.OrderStatus;
import com.shop.api.swagger.models.ShippingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "orders-collection")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    private String id;

    private Integer customerId;

    private OrderStatus status;

    private ShippingType shippingType;

    @Transient
    private String promocode;

    private Float total;
}
