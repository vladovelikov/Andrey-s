package com.example.eventsdemo.custom;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AddProductListener {

    @EventListener(AddProductEvent.class)
    public void onProductAdded(AddProductEvent addProductEvent) {
        //TODO: notify users
        //TODO: count the products

        System.out.println(addProductEvent);
    }
}
