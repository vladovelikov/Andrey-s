package com.example.eventsdemo.context;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ContextRefreshedEventListener {

    @EventListener
    public void onRefresh(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("Context event refreshed listener");
    }
}
