package com.example.petstorebootc51.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface Pet extends JpaRepository<Pet, Long> {
}
