package com.notifier.backend.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("SMSOrder")
public class SMSOrder {
    @Id
    private String id;
    private String message;
    private OrderStatus status;
    private LocalDateTime createdTime;

    public SMSOrder(String message) {
        this.message = message;
        this.status = OrderStatus.NEW;
        this.createdTime = LocalDateTime.now();
    }

    public SMSOrder() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }
}
