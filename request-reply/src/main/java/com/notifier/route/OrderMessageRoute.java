package com.notifier.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class OrderMessageRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet").bindingMode(RestBindingMode.auto);

        rest().path("/order").consumes("text/plain").produces("text/plain")
                .post().type(String.class).outType(String.class).to("direct:pub");

        from("direct:pub")
                .removeHeaders("*")
                .to("nats:sms.order?servers=127.0.0.1:49915")
                .transform().constant("Order received!");
    }

}

