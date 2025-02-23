package com.example.demo.controller;

import com.example.demo.model.dto.CreateOrderDto;
import com.example.demo.model.dto.OrderDto;
import com.example.demo.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private OrderService orderService;

    @PostMapping
    public OrderDto createOrder(@RequestBody CreateOrderDto orderDto) {
        log.info("Create order: {}", orderDto);
        return orderService.createOrder(orderDto);
    }

}
