package com.example.demo.service.retryable;

import com.example.demo.model.entity.RetryableTask;
import com.example.demo.mapper.RetryableTaskMapper;
import com.example.demo.service.DeliveryService;
import com.example.demo.service.RetryableTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SendCreateDeliveryRequestRetryableTaskProcessor implements RetryableTaskProcessor {

    private final DeliveryService deliveryService;

    private final RetryableTaskMapper retryableTaskMapper;

    private final RetryableTaskService retryableTaskService;


    @Override
    public void processRetryableTasks(List<RetryableTask> tasks) {
        List<RetryableTask> successRetryableTasks = new ArrayList<>();
        for(RetryableTask task : tasks){
            var isSuccess = processRetryableTask(task);
            if(isSuccess){
                successRetryableTasks.add(task);
            }
        }
        retryableTaskService.markRetryableTasksAsCompleted(successRetryableTasks);
    }

    private boolean processRetryableTask(RetryableTask task) {
        var order = retryableTaskMapper.convertJsonToOrder(task.getPayload());
        return deliveryService.processDelivery(order.getId(), order.getDeliveryAddress());
    }
}
