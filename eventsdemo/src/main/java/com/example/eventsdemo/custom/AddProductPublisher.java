package com.example.eventsdemo.custom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class AddProductPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public AddProductPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishEvent(String productId) {
        AddProductEvent addProductEvent = new AddProductEvent(this, productId);
        applicationEventPublisher.publishEvent(addProductEvent);
    }
}
