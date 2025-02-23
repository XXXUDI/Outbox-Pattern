package com.example.demo.client;

import com.example.demo.model.dto.CreateDeliveryDto;
import com.example.demo.model.dto.DeliveryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "delivery-service", url = "${integration.deliveryService.url}")
public interface DeliveryServiceClient {
    @PostMapping("/deliveries")
    DeliveryDto createDelivery(@RequestBody CreateDeliveryDto deliveryDto);
}
