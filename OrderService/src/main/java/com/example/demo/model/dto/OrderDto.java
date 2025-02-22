package com.example.demo.model.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class OrderDto {
    private UUID uuid;
    private Long customerId;
    private List<Long> productIds;
    private Integer quantity;
    private String deliveryAddress;
    private String paymentMethod;
    private String orderNotes;
}
