package com.example.eventsdemo.schedulingtasks;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CustomScheduler {

    private int seconds;

    @Scheduled(fixedDelay = 1000, initialDelay = 5000)
    public void doSomeWork() {
        System.out.println("I am starting on every second" + (++seconds));
    }

    @Scheduled(cron = "0 0 2 ? * SUN")
    public void scheduleByCron() {

    }
}
