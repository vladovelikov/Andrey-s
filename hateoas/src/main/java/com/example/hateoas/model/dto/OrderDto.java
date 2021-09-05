package com.example.hateoas.model.dto;

import com.example.hateoas.model.Order;

public class OrderDto {

    private Long id;
    private Long studentId;
    private Long courseId;

    public OrderDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public static OrderDto asDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setCourseId(order.getCourse().getId());
        orderDto.setStudentId(order.getStudent().getId());
        return orderDto;
    }
}
