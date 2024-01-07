package com.notifier.backend.route;

import com.notifier.backend.data.Message;
import com.notifier.backend.data.SMSOrder;
import com.notifier.backend.service.SMSOrderService;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ConsumeMessageRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("nats:sms.order?servers=nats-svc:4222")
                .bean("smsOrderService", "placeOrder" )
                .log("body ${body}");
//        .bean(OrderService.class, "doSomething(${body}, true)")
//        .to("bean:orderService?method=doSomething(null, true)")

    }

}

