package com.example.demo.scheduler;


import com.example.demo.mapper.RetryableTaskMapper;
import com.example.demo.model.entity.RetryableTask;
import com.example.demo.model.enums.RetryableTaskType;
import com.example.demo.service.DeliveryService;
import com.example.demo.service.RetryableTaskService;
import com.example.demo.service.retryable.RetryableTaskProcessor;
import com.example.demo.service.retryable.SendCreateDeliveryRequestRetryableTaskProcessor;
import com.example.demo.service.retryable.SendCreateNotificationRequestRetryableTaskProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class RetryableTaskScheduler {

    private final RetryableTaskService retryableTaskService;
    private final Map<RetryableTaskType, RetryableTaskProcessor> retryableTaskProcessors;

    // TODO: add Notification Service
    public RetryableTaskScheduler(RetryableTaskService retryableTaskService, RetryableTaskMapper retryableTaskMapper, DeliveryService deliveryService) {
        this.retryableTaskService = retryableTaskService;
        this.retryableTaskProcessors = Map.of(
                RetryableTaskType.SEND_CREATE_DELIVERY_REQUEST, new SendCreateDeliveryRequestRetryableTaskProcessor(deliveryService, retryableTaskMapper, retryableTaskService),
                RetryableTaskType.SEND_CREATE_NOTIFICATION_REQUEST, new SendCreateNotificationRequestRetryableTaskProcessor()
        );
    }

    @Scheduled(fixedRate = 5000)
    public void executeRetryableTasks(){
        log.info("Executing retryable tasks");

        for(Map.Entry<RetryableTaskType, RetryableTaskProcessor> entry : retryableTaskProcessors.entrySet()){
            var taskType = entry.getKey();
            var processor = entry.getValue();

            var retryableTasks = retryableTaskService.getRetryableTaskForProcessing(taskType);

            processor.processRetryableTasks(retryableTasks);
        }
    }

}
