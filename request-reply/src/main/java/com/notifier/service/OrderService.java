package com.notifier.service;

import com.notifier.data.SMSOrder;

public interface OrderService {
    SMSOrder order(String orderMessage);
}
