package com.example.demo.model.dto;

import lombok.Data;

@Data
public class CreateOrderDto {
    private Long customerId;
    private String deliveryAddress;
    private String paymentMethod;
    private String orderNotes;
    private String customerEmail;
}
