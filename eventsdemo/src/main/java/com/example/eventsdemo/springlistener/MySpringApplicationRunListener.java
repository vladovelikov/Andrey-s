package com.example.eventsdemo.springlistener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class MySpringApplicationRunListener implements SpringApplicationRunListener {

    public MySpringApplicationRunListener(SpringApplication springApplication, String args[]) {

    }

    @Override
    public void starting() {
        System.out.println("Spring Application Run Listener: Starting");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        System.out.println("Spring Application Run Listener: Environment Prepared");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println("Spring Application Run Listener: Context Prepared");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println("Spring Application Run Listener: Context Loaded");
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        System.out.println("Spring Application Run Listener: Started");
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        System.out.println("Spring Application Run Listener: Running");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        System.out.println("Spring Application Run Listener: Failed");
    }
}
