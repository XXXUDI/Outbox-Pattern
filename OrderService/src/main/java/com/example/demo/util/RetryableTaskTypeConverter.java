package com.example.demo.util;

import com.example.demo.model.enums.RetryableTaskType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RetryableTaskTypeConverter implements AttributeConverter<RetryableTaskType, String> {

    @Override
    public String convertToDatabaseColumn(RetryableTaskType attribute) {
        return attribute == null ? null : attribute.getValue();
    }

    @Override
    public RetryableTaskType convertToEntityAttribute(String dbData) {
        return dbData == null ? null : RetryableTaskType.fromValue(dbData);
    }

}
