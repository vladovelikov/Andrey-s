package com.example.hateoas.web;
import com.example.hateoas.model.Course;
import com.example.hateoas.model.Order;
import com.example.hateoas.model.Student;
import com.example.hateoas.model.dto.OrderDto;
import com.example.hateoas.repository.CourseRepository;
import com.example.hateoas.repository.OrderRepository;
import com.example.hateoas.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final StudentRepository studentRepository;
    private final OrderRepository orderRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public OrdersController(StudentRepository studentRepository, OrderRepository orderRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.orderRepository = orderRepository;
        this.courseRepository = courseRepository;
    }

    @PostMapping
    public ResponseEntity<EntityModel<OrderDto>> createOrder(@RequestBody OrderDto orderDto) {
        //TODO: validation

        Student student = this.studentRepository.getById(orderDto.getStudentId());
        Course course = this.courseRepository.getById(orderDto.getCourseId());
        Order newOrder = new Order();
        newOrder.setStudent(student);
        newOrder.setCourse(course);

        this.orderRepository.saveAndFlush(newOrder);

        //TODO: build order links

        return ResponseEntity.ok(EntityModel.of(OrderDto.asDto(newOrder)));
    }

}
