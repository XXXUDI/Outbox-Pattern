package com.example.demo.mapper;

import com.example.demo.model.entity.Order;
import com.example.demo.model.entity.RetryableTask;
import com.example.demo.model.enums.RetryableTaskStatus;
import com.example.demo.model.enums.RetryableTaskType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.Instant;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class, RetryableTaskStatus.class, Instant.class})
public interface RetryableTaskMapper {


    @Mapping(target = "id", expression = "java(UUID.randomUUID())")
    @Mapping(target = "version", constant = "0")
    @Mapping(target = "createdAt", expression = "java(Instant.now())")
    @Mapping(target = "updatedAt", expression = "java(Instant.now())")
    @Mapping(source = "order", target = "payload", qualifiedByName = "convertObjectToJson")
    @Mapping(target = "retryTime", expression = "java(Instant.now())")
    @Mapping(target = "status", expression = "java(RetryableTaskStatus.IN_PROGRESS)")
    RetryableTask toRetryableTask(Order order, RetryableTaskType retryableTaskType);

    @Named("convertObjectToJson")
    default String convertObjectToJson(Order order){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {
            return objectMapper.writeValueAsString(order);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting Order to JSON", e);
        }
    }

    @Named("convertJsonToOrder")
    default Order convertJsonToOrder(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {
            return objectMapper.readValue(json, Order.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert JSON to Order", e);
        }
    }
}
