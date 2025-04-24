package com.emphr.emphr.controller;

import com.emphr.emphr.entity.Department;
import com.emphr.emphr.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import javax.validation.Valid;
import org.springframework.validation.BindingResult;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin("*")
public class DepartmentController {

    private final DepartmentService departmentService;
    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // Fetch all departments
    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    // Add a new department with validation
    @PostMapping
    public ResponseEntity<Department> createDepartment(@Valid @RequestBody Department department, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            logger.error("Validation errors occurred: {}", bindingResult.getAllErrors());
            return ResponseEntity.badRequest().body(null);
        }

        try {
            Department savedDepartment = departmentService.addDepartment(department);
            return ResponseEntity.status(201).body(savedDepartment);
        } catch (Exception e) {
            logger.error("Error saving department: " + e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }
}
