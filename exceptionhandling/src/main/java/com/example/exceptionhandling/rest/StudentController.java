package com.example.exceptionhandling.rest;

import com.example.exceptionhandling.HelloException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class StudentController {

    @GetMapping("/student")
    public ResponseEntity<Student> getStudent() {
        throw new StudentNotFoundException("Student is not found");
    }

}
