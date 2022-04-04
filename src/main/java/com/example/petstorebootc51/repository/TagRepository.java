package com.example.petstorebootc51.repository;

import com.example.petstorebootc51.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}
