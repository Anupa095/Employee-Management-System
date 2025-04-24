package com.emphr.emphr.service;

import com.emphr.emphr.entity.Department;
import com.emphr.emphr.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private static final Logger logger = LoggerFactory.getLogger(DepartmentService.class);

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department addDepartment(Department department) {
        try {
            logger.info("Adding department: " + department.getName());
            return departmentRepository.save(department);
        } catch (Exception e) {
            logger.error("Error saving department: " + e.getMessage(), e);
            throw e; // Rethrow exception to be handled by the controller
        }
    }
}
