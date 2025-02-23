package com.example.demo.service;

import com.example.demo.model.entity.Order;
import com.example.demo.model.entity.RetryableTask;
import com.example.demo.model.enums.RetryableTaskStatus;
import com.example.demo.model.enums.RetryableTaskType;
import com.example.demo.mapper.RetryableTaskMapper;
import com.example.demo.repository.RetryableTaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RetryableTaskService {
    private final RetryableTaskRepository retryableTaskRepository;
    private final RetryableTaskMapper retryableTaskMapper;
    @Value("${retryabletask.timeoutInSeconds}")
    private Integer timeoutInSeconds;
    @Value("${retryabletask.limit}")
    private Integer limit;


    @Transactional
    public RetryableTask createRetryableTask(Order order, RetryableTaskType retryableTaskType) {
        RetryableTask retryableTask = retryableTaskMapper.toRetryableTask(order, retryableTaskType);
        return retryableTaskRepository.save(retryableTask);
    }

    @Transactional // Для того, щоб блокувати доступ для 2 шедулерів одночасно
    public List<RetryableTask> getRetryableTaskForProcessing(RetryableTaskType retryableTaskType) {
        var currentTime = Instant.now();
        Pageable pageable = PageRequest.of(0, limit);

        List<RetryableTask> tasks = retryableTaskRepository.findRetryableTasksForProcessing(
                retryableTaskType,
                RetryableTaskStatus.IN_PROGRESS,
                pageable,
                Instant.now()
                );

        for(RetryableTask task : tasks) {
            task.setRetryTime(currentTime.plus(Duration.ofSeconds(timeoutInSeconds)));
        }

        return tasks;
    }

    @Transactional
    public void markRetryableTasksAsCompleted(List<RetryableTask> retryableTasks) {
        for (RetryableTask retryableTask : retryableTasks) {
            retryableTask.setStatus(RetryableTaskStatus.SUCCESS);
        }
        retryableTaskRepository.saveAll(retryableTasks);
    }
}
