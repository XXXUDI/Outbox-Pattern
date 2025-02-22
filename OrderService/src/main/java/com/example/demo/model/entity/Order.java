package com.example.demo.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_table")
@Getter
@Setter
public class Order extends BaseEntity {

    private Long customerId;

    private String deliveryAddress;

    private String paymentMethod;

    private String orderNotes;

    private String customerEmail;

}
