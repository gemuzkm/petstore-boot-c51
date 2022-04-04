package com.example.petstorebootc51.repository;

import com.example.petstorebootc51.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
