package com.example.petstorebootc51.controller;

import com.example.petstorebootc51.entity.Order;
import com.example.petstorebootc51.entity.Pet;
import com.example.petstorebootc51.repository.OrderRepository;
import com.example.petstorebootc51.repository.PetRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store")
@Api(tags = "store", description = "Access to Petstore orders")
public class StoreController {
    private final OrderRepository orderRepository;

    public StoreController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @ApiOperation(value = "Place an order for a pet")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid Order")
    })
    @PostMapping(value = "/order", produces = "application/json")
    public ResponseEntity<Order> order(@RequestBody @ApiParam(value = "order placed for purchasing the pet") Order order,
                                       BindingResult bindingResult) {
        Order save = orderRepository.save(order);
        return ResponseEntity.ok(save);
    }

    @ApiOperation(value = "Find purchase order by ID",
            notes = "For valid response try integer IDs with value >= 1 and <= 10. Other values will generated exceptions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @GetMapping(value = "/order/{orderId}", produces = "application/json")
    public ResponseEntity<Order> getOrder(@PathVariable("orderId")
                                          @ApiParam(value = "ID of pet that needs to be fetched", example = "orderId") Long orderId,
                                          BindingResult bindingResult) {
        Order byId = orderRepository.getById(orderId);
        return ResponseEntity.ok(byId);
    }

    @ApiOperation(value = "Delete purchase order by ID",
            notes = "For valid response try integer IDs with positive integer value. Negative or non-integer values will generate API errors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    @DeleteMapping(value = "/order/{orderId}", produces = "application/json")
    public void deleteOrder(@PathVariable("orderId")
                            @ApiParam(value = "ID of the order that needs to be deleted", example = "orderId") Long orderId,
                            BindingResult bindingResult) {

    }

    @ApiOperation(value = "Returms pet inventories by status", notes = "Returns a map of status codes to quantities")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successful operation")
    })
    @GetMapping(value = "/inventory", produces = "application/json")
    public ResponseEntity<Object> getListInventory() {
        return ResponseEntity.ok(new Object());
    }

}
