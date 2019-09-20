package com.shop.model;

import com.shop.api.swagger.models.OrderStatus;
import com.shop.api.swagger.models.ShippingType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(generator = "increment")
    private Integer id;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "shipping_type")
    private ShippingType shippingType;

    @Transient
    private String promocode;

    @Column(name = "total")
    private Float total;
}
