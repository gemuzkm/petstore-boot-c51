package com.example.petstorebootc51.controller;

import com.example.petstorebootc51.entity.Pet;
import com.example.petstorebootc51.enums.PetStatus;
import com.example.petstorebootc51.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {
    private final PetRepository petRepository;

    public PetController(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @GetMapping("/{petId}")
    public ResponseEntity<Pet> findPetById(@PathVariable("petId") long petId, BindingResult bindingResult) {
        Pet byId = petRepository.getById(petId);

        return ResponseEntity.ok(byId);
    }

    @PostMapping("/{petId}")
    public ResponseEntity<Void> updateById(@PathVariable("petId") long petId, String name, String status) {

        Pet petUpdate = petRepository.getById(petId);
        petUpdate.setName(name);
        petUpdate.setStatus(PetStatus.valueOf(status));
        petRepository.save(petUpdate);
        return new ResponseEntity<>(HttpStatus.UPGRADE_REQUIRED);
    }

    @DeleteMapping("/{petId}")
    public HttpStatus delete(@PathVariable("petId") long petId, @RequestHeader("api_ley") String api_ley) {
        if (petRepository.existsById(petId)) {

        }  else {
            return HttpStatus.resolve(404);
        }

        return null;
    }

    @PostMapping
    public ResponseEntity<Pet> save(@Valid @RequestBody Pet pet, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(405).build();
        }

        Pet save = petRepository.save(pet);

        return ResponseEntity.ok(save);
    }
    
    @PutMapping
    public HttpStatus update(@Valid @RequestBody Pet pet, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return HttpStatus.BAD_REQUEST;
        }

        petRepository.save(pet);

        return null;
    }
    
    @GetMapping("/findByStatus")
    public ResponseEntity<List<Pet>> findByStatus(@RequestBody PetStatus status) {
        List<Pet> byStatus = petRepository.findByStatus(status);

        return ResponseEntity.ok(byStatus);
    }
}
