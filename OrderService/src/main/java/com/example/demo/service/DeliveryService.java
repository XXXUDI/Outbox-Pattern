package com.example.demo.service;

import com.example.demo.client.DeliveryServiceClient;
import com.example.demo.model.dto.CreateDeliveryDto;
import com.example.demo.model.dto.DeliveryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryService {

    private final DeliveryServiceClient deliveryServiceClient;

    public boolean processDelivery(UUID orderId, String deliveryAddress) {

        CreateDeliveryDto deliveryDto = buildRequest(orderId, deliveryAddress);
        boolean result = false;

        try {
            DeliveryDto delivery = deliveryServiceClient.createDelivery(deliveryDto);
            if ("success".equals(delivery.getStatus())) {
                log.info("Delivery for order with id {} was successfully created", orderId);
                result = true;
            } else {
                log.error("Delivery for order with id {} was not created", orderId);
            }
        } catch (Exception e) {
            log.error("Error occurred while creating delivery for order with id {}", orderId, e);
        }
        return result;
    }

    private CreateDeliveryDto buildRequest(UUID orderId, String deliveryAddress) {
        CreateDeliveryDto deliveryDto = new CreateDeliveryDto();
        deliveryDto.setOrderId(orderId);
        deliveryDto.setDeliveryAddress(deliveryAddress);
        return deliveryDto;
    }
}
