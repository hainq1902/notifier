package com.notifier.backend.service;

import com.notifier.backend.data.OrderStatus;
import com.notifier.backend.data.SMSOrder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SMSOrderService {
    String placeOrder(String message);
    Flux<SMSOrder> findSMSOrder(OrderStatus status);

    String updateOrder(String orderId, SMSOrder order);
}
