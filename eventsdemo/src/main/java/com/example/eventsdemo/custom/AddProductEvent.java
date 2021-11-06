package com.example.eventsdemo.custom;

import org.springframework.context.ApplicationEvent;

public class AddProductEvent extends ApplicationEvent {

    private final String msg;

    public AddProductEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AddProductEvent{");
        sb.append("msg='").append(msg).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
