package com.notifier.backend.service.impl;

import com.notifier.backend.data.OrderStatus;
import com.notifier.backend.data.SMSOrder;
import com.notifier.backend.repository.SMSOrderRepository;
import com.notifier.backend.service.SMSOrderService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.Objects;

@Service("smsOrderService")
public class SMSOrderServiceImpl implements SMSOrderService {

    private final SMSOrderRepository repository;

    public SMSOrderServiceImpl(SMSOrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public String placeOrder(String message) {

        var smsOrder = new SMSOrder(message);
        return Objects.requireNonNull(repository.save(smsOrder).block()).getId();
    }

    @Override
    public Flux<SMSOrder> findSMSOrder(OrderStatus status) {
        return repository.findAllByStatusAndCreatedTimeGreaterThanEqualOrderByCreatedTime(status,
                                                                                        LocalDate.now().atStartOfDay());
    }

    @Override
    public String updateOrder(String orderId, SMSOrder updatedOrder) {
        repository.findById(orderId)
                  .switchIfEmpty(Mono.error(new Exception("ORDER_NOT_FOUND")))
                  .map(order -> {
                      if (Objects.nonNull(updatedOrder.getStatus())) {
                          order.setStatus(updatedOrder.getStatus());
                      }
                      return order;
                  })
                  .flatMap(repository::save)
                  .subscribe();
        return orderId;
    }
}
