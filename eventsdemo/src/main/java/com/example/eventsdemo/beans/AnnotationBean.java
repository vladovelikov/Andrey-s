package com.example.eventsdemo.beans;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//@Component
public class AnnotationBean {

    @PostConstruct
    public void postConstruct() {
        System.out.println("Bean constructed");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("Bean is destroyed");
    }

}
