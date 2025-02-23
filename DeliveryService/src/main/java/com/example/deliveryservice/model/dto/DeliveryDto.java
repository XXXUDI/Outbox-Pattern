package com.example.deliveryservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class DeliveryDto {

    private UUID deliveryId;
    private String status;
}
