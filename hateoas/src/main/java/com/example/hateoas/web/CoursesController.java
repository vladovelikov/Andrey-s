package com.example.hateoas.web;
import com.example.hateoas.model.Course;
import com.example.hateoas.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
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

    private Link[] createCourseLinks(Course course) {
        List<Link> result = new ArrayList<>();

        //TODO

        return result.toArray(new Link[0]);
    }
}
