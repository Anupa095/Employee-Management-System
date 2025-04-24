package com.emphr.emphr.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name; // Full name (firstName + lastName merged here)
    private LocalDate dateOfBirth;
    private String gender;
    private String address;
    private String nationality;
    private String email;
    private String phone;

    // New fields for location details
    private String city;
    private String state;
    private String zipCode;

    private String department;

    // Optional: You can later add more fields if needed in the future
}
