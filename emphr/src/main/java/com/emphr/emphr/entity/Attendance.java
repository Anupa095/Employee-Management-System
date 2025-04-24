package com.emphr.emphr.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data  // Lombok annotation generates getters, setters, equals, hashCode, and toString methods automatically
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long employeeId;
    private String status;  // IN, OFF, Late, Absent
    private String clockInTime;
    private String clockOutTime;
    private String date;
}
