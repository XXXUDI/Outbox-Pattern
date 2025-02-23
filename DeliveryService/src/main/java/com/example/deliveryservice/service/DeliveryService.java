package com.example.deliveryservice.service;

import com.example.deliveryservice.model.dto.CreateDeliveryDto;
import com.example.deliveryservice.model.dto.DeliveryDto;
import com.example.deliveryservice.model.enums.CreateDeliveryStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class DeliveryService {
    public DeliveryDto createDelivery(CreateDeliveryDto request) {
        return new DeliveryDto(UUID.randomUUID(), CreateDeliveryStatus.SUCCESS.getValue());
    }
}
