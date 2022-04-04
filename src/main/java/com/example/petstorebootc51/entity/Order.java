package com.example.petstorebootc51.entity;

import com.example.petstorebootc51.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long petId;
    private int quantity;
    private LocalDateTime shipDate;
    private OrderStatus status;
    private boolean complete;
}
