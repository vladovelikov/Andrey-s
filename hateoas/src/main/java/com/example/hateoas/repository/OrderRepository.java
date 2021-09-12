package com.example.hateoas.repository;
import com.example.hateoas.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.student.id =: studentId")
    List<Order> findAllByStudentId(Long studentId);
}
