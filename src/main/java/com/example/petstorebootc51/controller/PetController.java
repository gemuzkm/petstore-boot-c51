package com.example.petstorebootc51.controller;

import com.example.petstorebootc51.entity.Pet;
import com.example.petstorebootc51.enums.PetStatus;
import com.example.petstorebootc51.repository.PetRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/pet")
@Api(tags = "pet", description = "Everything about your Pets")
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

        } else {
            return HttpStatus.resolve(404);
        }

        return null;
    }

    @ApiOperation(value = "Add a new pet the store")
    @PostMapping
    public void save(@Valid @RequestBody
                     @ApiParam(value = "Pet object that needs to be added to the store") Pet pet,
                     BindingResult bindingResult) {
        Pet save = petRepository.save(pet);
    }

    @ApiOperation(value = "Update an existing pet")
    @PutMapping
    public void update(@RequestBody
                             @ApiParam(value = "Pet object that needs to be added to the store")  Pet pet,
                             BindingResult bindingResult) {
        petRepository.save(pet);
    }

    @ApiOperation(value = "Find Pets by Status", notes = "Multiple status values can be provided with comma separated strings")
    @GetMapping("/findByStatus")
    public ResponseEntity<List<Pet>> findByStatus(@RequestBody
                                                  @ApiParam(value = "Status values that need to be considered for filter\n " +
                                                          "Available values : available, pending, sold")
                                                  PetStatus[] status) {
        List<Pet> byStatus = new ArrayList<>();

        for (int i = 0; i < status.length; i++) {
            byStatus = petRepository.findAllByStatus(status[i]);
        }

        return ResponseEntity.ok(byStatus);
    }
}
