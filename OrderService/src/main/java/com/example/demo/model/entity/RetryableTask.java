package com.example.demo.model.entity;

import com.example.demo.model.enums.RetryableTaskStatus;
import com.example.demo.model.enums.RetryableTaskType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnTransformer;

import java.time.Instant;

@Getter
@Setter
@Entity
public class RetryableTask extends BaseEntity{

    @Column(columnDefinition = "jsonb")
    @ColumnTransformer(write = "?::jsonb")
    private String payload;

    @Convert(converter = RetryableTaskType.class)
    private RetryableTaskType type;

    @Convert(converter = RetryableTaskStatus.class)
    private RetryableTaskStatus status;

    private Instant retryTime;
}
