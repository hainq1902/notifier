package com.notifier.backend.controller;

import com.notifier.backend.data.OrderStatus;
import com.notifier.backend.data.SMSOrder;
import com.notifier.backend.repository.SMSOrderRepository;
import com.notifier.backend.service.SMSOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class SMSOrderController {
    private static final int DELAY_PER_ITEM_MS = 100;

    private final SMSOrderService smsOrderService;

    public SMSOrderController(SMSOrderService smsOrderService) {
        this.smsOrderService = smsOrderService;
    }

    @GetMapping(path = "/orders"
//            , produces = MediaType.TEXT_EVENT_STREAM_VALUE
    )
    public Flux<SMSOrder> getOrders() {
        return smsOrderService.findSMSOrder(OrderStatus.NEW);
//        return smsOrderService.findSMSOrder(OrderStatus.NEW).delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS));
    }

    @PostMapping(path = "/orders/{orderId}",
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@RequestBody SMSOrder order,
                                            @PathVariable("orderId") String orderId) {
        var updatedOrder = smsOrderService.updateOrder(orderId, order);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }
}
