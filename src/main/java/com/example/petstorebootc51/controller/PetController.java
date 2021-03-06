package com.example.petstorebootc51.controller;

import com.example.petstorebootc51.entity.Pet;
import com.example.petstorebootc51.enums.PetStatus;
import com.example.petstorebootc51.repository.PetRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @ApiOperation(value = "Find pet by ID", notes = "Returns a single pet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Pet not found")
    })
    @GetMapping(value = "/{petId}", produces = "application/json")
    public ResponseEntity<Pet> findPetById(@PathVariable("petId")
                                           @ApiParam(value = "ID of pet to return", example = "petId") long petId,
                                           BindingResult bindingResult) {
        Pet byId = petRepository.getById(petId);

        return ResponseEntity.ok(byId);
    }

    @ApiOperation(value = "Updates a pet in the store with from data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "405", description = "Invalid input")
    })
    @PostMapping(value = "/{petId}", produces = "application/json")
    public void updateById(@PathVariable("petId")
                           @ApiParam(value = "ID of pet that needs to be updated", example = "petId") long petId,
                           @ApiParam(value = "Updated name of the pet", example = "name") String name,
                           @ApiParam(value = "Updated status of the pet", example = "status") String status) {

        Pet petUpdate = petRepository.getById(petId);
        petUpdate.setName(name);
        petUpdate.setStatus(PetStatus.valueOf(status));
        petRepository.save(petUpdate);
    }

    @ApiOperation(value = "Delete a pet", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Pet not found")
    })
    @DeleteMapping(value = "/{petId}", produces = "application/json")
    public void delete(@PathVariable("petId")
                       @ApiParam(value = "Pet id to delete", example = "petId") long petId,
                       @RequestHeader("api_ley") @ApiParam(value = " ", example = "api_key") String api_key) {
    }

    @ApiOperation(value = "Add a new pet the store", produces = "application/json")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "405", description = "Invalid input")
    })
    @PostMapping(produces = "application/json")
    public void save(@Valid @RequestBody
                     @ApiParam(value = "Pet object that needs to be added to the store") Pet pet,
                     BindingResult bindingResult) {
        Pet save = petRepository.save(pet);
    }

    @ApiOperation(value = "Update an existing pet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Pet not found"),
            @ApiResponse(responseCode = "405", description = "Invalid input")
    })
    @PutMapping(produces = "application/json")
    public void update(@RequestBody
                       @ApiParam(value = "Pet object that needs to be added to the store") Pet pet,
                       BindingResult bindingResult) {
        petRepository.save(pet);
    }

    @ApiOperation(value = "Find Pets by Status", notes = "Multiple status values can be provided with comma separated strings")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid status value")
    })
    @GetMapping(value = "/findByStatus", produces = "application/json")
    public ResponseEntity<List<Pet>> findByStatus(@RequestBody
                                                  @ApiParam(value = "Status values that need to be considered for filter\n " +
                                                          "Available values : available, pending, sold")
                                                          PetStatus[] status) {
        List<Pet> byStatus = new ArrayList<>();
        for (int i = 0; i < status.length; i++) {
            byStatus.addAll(petRepository.findAllByStatus(status[i]));
        }

        return ResponseEntity.ok(byStatus);
    }
}
