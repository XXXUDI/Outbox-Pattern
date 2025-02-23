package com.example.demo.service.retryable;

import com.example.demo.model.entity.RetryableTask;

import java.util.List;

public interface RetryableTaskProcessor {

    void processRetryableTasks(List<RetryableTask> tasks);
}
