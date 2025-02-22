package com.example.demo.repository;

import com.example.demo.model.entity.RetryableTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RetryableTaskRepository extends CrudRepository<RetryableTask, UUID> {

}
