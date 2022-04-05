package com.example.petstorebootc51.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ApiResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;
    private String message;
}
