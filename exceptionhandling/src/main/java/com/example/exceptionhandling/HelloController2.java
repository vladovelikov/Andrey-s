package com.example.exceptionhandling;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController2 {

    @GetMapping("/crash-me2")
    public ModelAndView crashMe() {
        throw new HelloException("I crashed!");
    }


}
