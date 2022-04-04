package com.example.petstorebootc51.repository;

import com.example.petstorebootc51.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
