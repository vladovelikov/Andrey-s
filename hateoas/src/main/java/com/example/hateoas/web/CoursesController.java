package com.example.hateoas.web;
import com.example.hateoas.model.Course;
import com.example.hateoas.model.dto.OrderDto;
import com.example.hateoas.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/courses")
public class CoursesController {

    private final CourseRepository courseRepository;

    @Autowired
    public CoursesController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Course>>> getAllCourses() {
        List<EntityModel<Course>> courses = this.courseRepository.findAll().stream()
                .map(course -> EntityModel.of(course, createCourseLinks(course)))
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(courses,
                linkTo(methodOn(CoursesController.class).getAllCourses()).withSelfRel()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Course>> getCourse(@PathVariable Long id) {
        Optional<Course> optionalCourse = this.courseRepository.findById(id);

        return optionalCourse.map(course -> EntityModel.of(course, createCourseLinks(course)))
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EntityModel<Course>> createCourse(@RequestBody Course course) {
        if (course.getId() != null) {
            return ResponseEntity.badRequest().build();
        }

        Course result = this.courseRepository.saveAndFlush(course);

        return ResponseEntity.ok(EntityModel.of(result, createCourseLinks(course)));
    }

    private Link[] createCourseLinks(Course course) {
        List<Link> result = new ArrayList<>();

        Link self = linkTo(methodOn(CoursesController.class).getCourse(course.getId())).withSelfRel();
        result.add(self);

        if(course.isEnabled()) {
            OrderDto orderDto = new OrderDto();
            orderDto.setCourseId(course.getId());
            Link enroll = linkTo(methodOn(OrdersController.class).createOrder(orderDto))
                    .withRel("enroll");
            result.add(enroll);
        }

        return result.toArray(new Link[0]);
    }
}
