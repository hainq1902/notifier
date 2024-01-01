package com.notifier.data;

public class SMSOrder {
    private int id;
    private Message message;
    private OrderStatus status;

    public SMSOrder(Message message) {
        this.message = message;
        this.status = OrderStatus.NEW;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }
}
