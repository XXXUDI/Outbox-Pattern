package com.example.demo.util;

import com.example.demo.model.enums.RetryableTaskStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RetryableTaskStatusConverter implements AttributeConverter<RetryableTaskStatus, String> {

    @Override
    public String convertToDatabaseColumn(RetryableTaskStatus attribute) {
        return attribute == null ? null : attribute.getValue();
    }

    @Override
    public RetryableTaskStatus convertToEntityAttribute(String dbData) {
        return dbData == null ? null : RetryableTaskStatus.fromValue(dbData);
    }

}
