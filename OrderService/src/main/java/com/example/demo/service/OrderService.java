package com.example.demo.service;


import com.example.demo.mapper.OrderMapper;
import com.example.demo.model.dto.CreateOrderDto;
import com.example.demo.model.dto.OrderDto;
import com.example.demo.model.enums.RetryableTaskType;
import com.example.demo.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final RetryableTaskService retryableTaskService;


    @Transactional
    public OrderDto createOrder(CreateOrderDto orderDto) {
        var order = orderRepository.save(orderMapper.toEntity(orderDto));

        retryableTaskService.createRetryableTask(order, RetryableTaskType.SEND_CREATE_DELIVERY_REQUEST);
        retryableTaskService.createRetryableTask(order, RetryableTaskType.SEND_CREATE_NOTIFICATION_REQUEST);

        return orderMapper.toDto(order);
    }
}
