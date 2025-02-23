package com.example.demo.service.retryable;

import com.example.demo.model.entity.RetryableTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SendCreateNotificationRequestRetryableTaskProcessor implements RetryableTaskProcessor {
    @Override
    public void processRetryableTasks(List<RetryableTask> tasks) {

    }
}
