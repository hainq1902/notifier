package com.notifier.backend.repository;

import com.notifier.backend.data.OrderStatus;
import com.notifier.backend.data.SMSOrder;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Repository("smsOrderRepository")
public interface SMSOrderRepository extends ReactiveMongoRepository<SMSOrder, String> {
    Flux<SMSOrder> findAllByStatusAndCreatedTimeGreaterThanEqualOrderByCreatedTime(OrderStatus orderStatus,
                                                                                   LocalDateTime createdTime);

    Flux<SMSOrder> findAllByCreatedTimeGreaterThanEqualOrderByStatusDescProcessedTimeDescCreatedTime(
                                                                                        LocalDateTime createdTime);
}
