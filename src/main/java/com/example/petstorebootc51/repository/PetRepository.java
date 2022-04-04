package com.example.petstorebootc51.repository;

import com.example.petstorebootc51.entity.Pet;
import com.example.petstorebootc51.enums.PetStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Component
@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByStatus(PetStatus status);
}
