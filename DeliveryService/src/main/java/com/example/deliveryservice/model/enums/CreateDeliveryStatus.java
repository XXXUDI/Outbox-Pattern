package com.example.deliveryservice.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CreateDeliveryStatus {
    SUCCESS("success"),
    FAILED("failed");
    private String value;
}

