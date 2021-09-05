package com.example.hateoas.web;
import com.example.hateoas.model.Student;
import com.example.hateoas.model.dto.OrderDto;
import com.example.hateoas.repository.CourseRepository;
import com.example.hateoas.repository.OrderRepository;
import com.example.hateoas.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/students")
public class StudentsController {

    private final StudentRepository studentRepository;
    private final OrderRepository orderRepository;
    private final CourseRepository courseRepository;


    @Autowired
    public StudentsController(StudentRepository studentRepository, OrderRepository orderRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.orderRepository = orderRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Student>>> getAllStudents() {
        List<EntityModel<Student>> students = this.studentRepository.findAll().stream()
                .map(student -> EntityModel.of(student, createStudentLinks(student)))
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(students,
                linkTo(methodOn(StudentsController.class).getAllStudents()).withSelfRel()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Student>> getStudent(@PathVariable Long id) {
        Optional<Student> optionalStudent = this.studentRepository.findById(id);

        return optionalStudent.map(student -> EntityModel.of(student, createStudentLinks(student)))
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/orders")
    public ResponseEntity<CollectionModel<EntityModel<OrderDto>>> getOrdersByStudentId(
            @PathVariable(name = "id") Long studentId) {

        List<EntityModel<OrderDto>> orders = this.orderRepository.findAllByStudentId(studentId)
                .stream().map(OrderDto::asDto)
                .map(dto -> EntityModel.of(dto))
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(orders,
                linkTo(methodOn(StudentsController.class).getOrdersByStudentId(studentId)).withSelfRel()));
    }


    //metod vrashtasht vsi4ki studenti

    //metod vrashtasht order-ite na edin student

    private Link[] createStudentLinks(Student student) {
        List<Link> links = new ArrayList<>();

        Link self = linkTo(methodOn(StudentsController.class).getStudent(student.getId())).withSelfRel();
        links.add(self);

        return links.toArray(new Link[0]);
    }
}
