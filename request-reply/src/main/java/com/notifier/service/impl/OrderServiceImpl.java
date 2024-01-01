package com.notifier.service.impl;

import com.notifier.data.Message;
import com.notifier.data.SMSOrder;
import com.notifier.data.OrderStatus;
import com.notifier.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    public SMSOrder order(String orderMessage) {
        var message = new Message(orderMessage, LocalDateTime.now());

        return new SMSOrder(message);
    }
}
