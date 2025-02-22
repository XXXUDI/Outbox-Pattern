package com.example.demo.service;

import com.example.demo.model.entity.Order;
import com.example.demo.model.entity.RetryableTask;
import com.example.demo.model.enums.RetryableTaskType;
import com.example.demo.repository.RetryableTaskMapper;
import com.example.demo.repository.RetryableTaskRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class RetryableTaskService {
    private final RetryableTaskRepository retryableTaskRepository;
    private final RetryableTaskMapper retryableTaskMapper;


    @Transactional
    public RetryableTask createRetryableTask(Order order, RetryableTaskType retryableTaskType) {
        RetryableTask retryableTask = retryableTaskMapper.toRetryableTask(order, retryableTaskType);
        return retryableTaskRepository.save(retryableTask);
    }
}
