package com.example.deliveryservice.model.dto;


import lombok.Data;

import java.util.UUID;

@Data
public class CreateDeliveryDto {

    public UUID orderId;
    public String deliveryAddress;
}
