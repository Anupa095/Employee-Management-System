package com.emphr.emphr.controller;

import com.emphr.emphr.entity.Department;
import com.emphr.emphr.service.DepartmentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin(origins = "*")
public class DepartmentController {

    private final DepartmentService departmentService;
    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // Fetch all departments
    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        try {
            List<Department> departments = departmentService.getAllDepartments();
            return ResponseEntity.ok(departments);
        } catch (Exception e) {
            logger.error("Error fetching departments: {}", e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }

    // Add a new department with validation
    @PostMapping
    public ResponseEntity<?> createDepartment(@Valid @RequestBody Department department) {
        try {
            Department savedDepartment = departmentService.addDepartment(department);
            return ResponseEntity.status(201).body(savedDepartment);
        } catch (Exception e) {
            logger.error("Error saving department: {}", e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }

    // ✅ DELETE Department by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        try {
            departmentService.deleteDepartment(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.error("Error deleting department: {}", e.getMessage(), e);
            return ResponseEntity.status(500).build();
        }
    }
}
