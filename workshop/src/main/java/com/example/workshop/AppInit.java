package com.example.workshop;
import com.example.workshop.student.Student;
import com.example.workshop.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInit implements CommandLineRunner {

    private final StudentRepository studentRepository;

    @Autowired
    public AppInit(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        Student student = new Student();
        student.setFirstName("Victor");
        student.setLastName("Balev");
        this.studentRepository.saveAndFlush(student);
    }
}
