package com.example.deliveryservice.controller;

import com.example.deliveryservice.model.dto.CreateDeliveryDto;
import com.example.deliveryservice.model.dto.DeliveryDto;
import com.example.deliveryservice.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/v1/deliveries")
@RequiredArgsConstructor
public class DeliveryController {


    private final DeliveryService deliveryService;

    @PostMapping
    public DeliveryDto createDelivery(@RequestBody CreateDeliveryDto creativeDeliveryDto) {
        log.info("Received create delivery request: {}", creativeDeliveryDto);

        var deliveryResponse = deliveryService.createDelivery(creativeDeliveryDto);

        log.info("Delivery response: {}", deliveryResponse);

        return deliveryResponse;
    }
}
