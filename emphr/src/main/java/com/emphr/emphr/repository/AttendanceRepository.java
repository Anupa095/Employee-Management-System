package com.emphr.emphr.repository;

import com.emphr.emphr.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  AttendanceRepository extends JpaRepository<Attendance, Long> {

    // Find attendance records by employee ID
    List<Attendance> findByEmployeeId(Long employeeId);

    // Find attendance records by status (e.g., Present, Absent)
    List<Attendance> findByStatus(String status);

    // Find attendance records by employee ID and date
    List<Attendance> findByEmployeeIdAndDate(Long employeeId, String date);

    // Find all attendance records (inherited from JpaRepository)
    List<Attendance> findAll();
}
