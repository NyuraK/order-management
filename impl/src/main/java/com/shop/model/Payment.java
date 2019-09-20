package com.shop.model;

import com.shop.api.swagger.models.PaymentType;
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
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(generator = "increment")
    private Integer id;

    @OneToOne
    @JoinColumn(name="id")
    private Order order;

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "payment_type")
    private PaymentType paymentType;

}
