package com.test.demospring;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MyEventHandler {

    @EventListener
    //@Order(Ordered.HIGHEST_PRECEDENCE+3)
    @Async
    public void handler(MyEvent event) {
        System.out.println(Thread.currentThread().toString());
        System.out.println("이벤트 받음 데이터는 "+ event.getData());
    }

    @EventListener
    @Async
    public void handler(ContextRefreshedEvent event) {
        System.out.println(Thread.currentThread().toString());
        System.out.println("ContextRefreshedEvent ");
    }

    @EventListener
    @Async
    public void handler(ContextClosedEvent event) {
        System.out.println(Thread.currentThread().toString()+":: ContextClosedEvent");
        System.out.println("ContextClosedEvent ");
    }
}
