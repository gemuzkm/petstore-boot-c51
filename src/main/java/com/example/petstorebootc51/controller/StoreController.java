package com.example.petstorebootc51.controller;

import com.example.petstorebootc51.entity.Order;
import com.example.petstorebootc51.entity.Pet;
import com.example.petstorebootc51.repository.OrderRepository;
import com.example.petstorebootc51.repository.PetRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/store")
@Api(tags = "store", description = "Access to Petstore orders")
public class StoreController {
    @Autowired
    private OrderRepository orderRepository;

    @ApiOperation(value = "Place an order for a pet")
    @PostMapping("/order")
    public ResponseEntity<Order> order(@RequestBody @ApiParam(value = "order placed for purchasing the pet") Order order,
                                       BindingResult bindingResult) {
        Order save = orderRepository.save(order);
        return ResponseEntity.ok(save);
    }

    @ApiOperation(value = "Find purchase order by ID",
            notes = "For valid response try integer IDs with value >= 1 and <= 10. Other values will generated exceptions")
    @GetMapping("/order/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable("orderId")
                                          @ApiParam(value = "ID of pet that needs to be fetched", example = "orderId") Long orderId,
                                          BindingResult bindingResult) {
        Order byId = orderRepository.getById(orderId);
        return ResponseEntity.ok(byId);
    }

    @ApiOperation(value = "Delete purchase order by ID",
            notes = "For valid response try integer IDs with positive integer value. Negative or non-integer values will generate API errors")
    @DeleteMapping("/order/{orderId}")
    public void deleteOrder(@PathVariable("orderId")
                            @ApiParam(value = "ID of the order that needs to be deleted", example = "orderId") Long orderId,
                            BindingResult bindingResult) {

    }

    @ApiOperation(value = "Returms pet inventories by status", notes = "Returns a map of status codes to quantities")
    @GetMapping("/inventory")
    public ResponseEntity<Object> getListInventory() {
        return ResponseEntity.ok(new Object());
    }

}
