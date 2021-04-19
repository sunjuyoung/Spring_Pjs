package com.test.demospring;

import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AnotherHandler {

    @EventListener
    //@Order(Ordered.HIGHEST_PRECEDENCE+1)
    @Async
    public void handler(MyEvent myEvent){
        System.out.println(Thread.currentThread().toString());
        System.out.println("another handler:"+myEvent.getData());
    }
}
