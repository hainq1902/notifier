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
                .to("nats:sms.order?servers=nats-svc:4222")
                .transform().constant("Order received!");
    }
//    nats-svc.nats-ns.svc.cluster.local
//    my-svc.my-namespace.svc.cluster-domain.example

}

