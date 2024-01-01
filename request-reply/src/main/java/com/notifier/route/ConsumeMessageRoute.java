package com.notifier.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class ConsumeMessageRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("nats:sms.order?servers=127.0.0.1:49915")
                .log("body ${body}");
    }

}

