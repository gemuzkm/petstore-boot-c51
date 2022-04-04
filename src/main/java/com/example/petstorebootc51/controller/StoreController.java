package com.example.petstorebootc51.controller;

import com.example.petstorebootc51.entity.Order;
import com.example.petstorebootc51.entity.Pet;
import com.example.petstorebootc51.repository.OrderRepository;
import com.example.petstorebootc51.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store")
public class StoreController {
    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/order")
    public ResponseEntity<Order> order(@RequestBody Order order, BindingResult bindingResult) {
        Order save = orderRepository.save(order);
        return ResponseEntity.ok(save);
    }

}
