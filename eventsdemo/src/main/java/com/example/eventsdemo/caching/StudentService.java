package com.example.eventsdemo.caching;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class StudentService {

    @Cacheable("students")
    public List<Student> getAllStudents() {
        System.out.println("I am doing complicated work to generate students");

        return generateStudents();
    }

    @CachePut("students")
    public List<Student> updateAllStudents() {
        System.out.println("I am updating all students");
        return generateStudents();
    }

    @CacheEvict(cacheNames = "students", allEntries = true)
    public void evictCache() {
        System.out.println("Evict cache");
    }

    private List<Student> generateStudents() {
        Random studentAge = new Random();
        Student firstStudent = new Student("Pesho", studentAge.nextInt(10));
        Student secondStudent = new Student("Ani", studentAge.nextInt(10));

        return List.of(firstStudent, secondStudent);

    }
}
