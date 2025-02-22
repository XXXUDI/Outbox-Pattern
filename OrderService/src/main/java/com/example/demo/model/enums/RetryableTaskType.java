package com.example.demo.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RetryableTaskType {
    SEND_CREATE_NOTIFICATION_REQUEST("SEND CREATE NOTIDICATION REQUEST"),
    SEND_CREATE_DELIVERY_REQUEST("SEND CREATE DELIVERY REQUEST");

    private String value;

    public static RetryableTaskType fromValue(String value) {
        for (RetryableTaskType status : RetryableTaskType.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown type: " + value);
    }

}
