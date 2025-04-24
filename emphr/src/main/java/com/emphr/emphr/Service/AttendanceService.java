package com.emphr.emphr.service;

import com.emphr.emphr.entity.Attendance;
import com.emphr.emphr.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    // Save a new attendance record
    public Attendance saveAttendance(Attendance attendance) {
        // Check if attendance already exists for this employee on the same date with the same status
        List<Attendance> existingAttendance = attendanceRepository.findByEmployeeIdAndDate(attendance.getEmployeeId(), attendance.getDate());

        // If no record exists for that employee on the same day, save the new record
        if (existingAttendance.isEmpty() || !existingAttendance.stream().anyMatch(a -> a.getStatus().equals(attendance.getStatus()))) {
            return attendanceRepository.save(attendance);
        } else {
            throw new IllegalStateException("Attendance record for this status already exists on this date.");
        }
    }

    // Get all attendance records for a specific employee
    public List<Attendance> getAttendanceByEmployeeId(Long employeeId) {
        return attendanceRepository.findByEmployeeId(employeeId);
    }

    // Get all attendance records (new method)
    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }
}
