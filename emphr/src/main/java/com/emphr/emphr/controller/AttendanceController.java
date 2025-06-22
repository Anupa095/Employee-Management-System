package com.emphr.emphr.controller;

import com.emphr.emphr.entity.Attendance;
import com.emphr.emphr.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    // Endpoint to record attendance (POST)
    @PostMapping
    public ResponseEntity<?> recordAttendance(@RequestBody Attendance attendance) {
        try {
            Attendance savedAttendance = attendanceService.saveAttendance(attendance);
            return ResponseEntity.ok(savedAttendance);
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("Error recording attendance: " + e.getMessage());
        }
    }

    // Endpoint to get all attendance records for a specific employee (GET)
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Attendance>> getAttendanceByEmployeeId(@PathVariable Long employeeId) {
        List<Attendance> attendances = attendanceService.getAttendanceByEmployeeId(employeeId);
        return ResponseEntity.ok(attendances);
    }

    // Endpoint to get all attendance records (GET)
    @GetMapping("/all")
    public ResponseEntity<List<Attendance>> getAllAttendance() {
        List<Attendance> attendances = attendanceService.getAllAttendance();
        return ResponseEntity.ok(attendances);
    }

    // -------- Update an attendance record (PUT) --------
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAttendance(@PathVariable Long id, @RequestBody Attendance updatedAttendance) {
        try {
            Optional<Attendance> existingAttendance = attendanceService.getAttendanceById(id);
            if (existingAttendance.isPresent()) {
                Attendance attendanceToUpdate = existingAttendance.get();
                // Update fields (match your entity fields)
                attendanceToUpdate.setDate(updatedAttendance.getDate());
                attendanceToUpdate.setStatus(updatedAttendance.getStatus());
                attendanceToUpdate.setEmployeeId(updatedAttendance.getEmployeeId());
                Attendance savedAttendance = attendanceService.saveAttendance(attendanceToUpdate);
                return ResponseEntity.ok(savedAttendance);
            } else {
                return ResponseEntity.status(404).body("Attendance record not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("Error updating attendance: " + e.getMessage());
        }
    }

    // -------- Delete an attendance record (DELETE) --------
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAttendance(@PathVariable Long id) {
        try {
            boolean deleted = attendanceService.deleteAttendance(id);
            if (deleted) {
                return ResponseEntity.ok("Attendance deleted successfully.");
            } else {
                return ResponseEntity.status(404).body("Attendance record not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("Error deleting attendance: " + e.getMessage());
        }
    }
}
