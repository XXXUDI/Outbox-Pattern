package com.example.demo.repository;

import com.example.demo.model.entity.RetryableTask;
import com.example.demo.model.enums.RetryableTaskStatus;
import com.example.demo.model.enums.RetryableTaskType;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public interface RetryableTaskRepository extends CrudRepository<RetryableTask, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT r from  RetryableTask r where r.type = :type and r.retryTime <= :retryTime " +
    "and r.status = :status " +
    "order by r.retryTime asc")
    public List<RetryableTask> findRetryableTasksForProcessing(RetryableTaskType type,
                                                               RetryableTaskStatus status,
                                                               Pageable pageable,
                                                               Instant retryTime);

}
