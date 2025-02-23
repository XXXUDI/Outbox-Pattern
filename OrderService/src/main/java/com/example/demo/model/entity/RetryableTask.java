package com.example.demo.model.entity;

import com.example.demo.model.enums.RetryableTaskStatus;
import com.example.demo.model.enums.RetryableTaskType;
import com.example.demo.util.RetryableTaskStatusConverter;
import com.example.demo.util.RetryableTaskTypeConverter;
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

    @Column(columnDefinition = "JSON")
    private String payload;

    @Convert(converter = RetryableTaskTypeConverter.class)
    private RetryableTaskType type;

    @Convert(converter = RetryableTaskStatusConverter.class)
    private RetryableTaskStatus status;

    private Instant retryTime;
}
