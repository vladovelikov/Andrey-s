package com.example.hateoas;
import com.example.hateoas.model.Course;
import com.example.hateoas.model.Order;
import com.example.hateoas.model.Student;
import com.example.hateoas.repository.CourseRepository;
import com.example.hateoas.repository.OrderRepository;
import com.example.hateoas.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class HateoasApplicationInit implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public HateoasApplicationInit(StudentRepository studentRepository, CourseRepository courseRepository, OrderRepository orderRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Student firstStudent = new Student();
        firstStudent.setName("Ivan");
        firstStudent.setAge(21);

        this.studentRepository.saveAndFlush(firstStudent);

        Course springDataCourse = new Course();
        springDataCourse.setEnabled(true);
        springDataCourse.setName("Spring Data");
        springDataCourse.setPrice(100);

        this.courseRepository.saveAndFlush(springDataCourse);

        Course springBatchCourse = new Course();
        springBatchCourse.setEnabled(false);
        springBatchCourse.setName("Spring Batch");
        springBatchCourse.setPrice(150);

        this.courseRepository.saveAndFlush(springBatchCourse);

        Order ivanSpringData = new Order();
        ivanSpringData.setStudent(firstStudent);
        ivanSpringData.setCourse(springDataCourse);

        this.orderRepository.saveAndFlush(ivanSpringData);





    }
}
